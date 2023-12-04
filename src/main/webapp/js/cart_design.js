import * as THREE from 'three'
import {GLTFLoader} from 'GLTFLoader'
import {OrbitControls} from 'OrbitControls';

const canvas = document.querySelector(".webgl");
let scene, camera, renderer, loader, root, controls,
    light1, light2, light3, light4

let build3DLowTop = (textureData, uuid) => {
    if (renderer != null) {
        renderer.dispose()
    }
    if (scene == null) {
        scene = new THREE.Scene();
    }

    if (camera == null) {
        camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
    }

    camera.position.z = 1.5
    controls = new OrbitControls(camera, canvas);
    renderer = new THREE.WebGLRenderer({
        canvas: canvas,
        antialias: true
    })

    renderer.setClearColor("#eaeaea");
    renderer.setSize(window.innerWidth / 2, window.innerWidth / 5);
    camera.updateProjectionMatrix();

    loader = new GLTFLoader();
    let elemFront = null;
    let elemFooter = null;
    let elemLace = null;
    let elemMiniString = null;
    let elemInner = null;
    let elemPin = null;
    let elemPad = null;

    loader.load('http://localhost:8080/ShoeStylize_Tomcat/assets/models/low-tops/shoe.gltf', (gltf) => {
        root = gltf.scene;
        scene.add(root);

        root.traverse(function (child) {
            if (child instanceof THREE.Mesh) {
                //child.material.emissive.set(0xBBBBBB);
            }
        });

        elemFront = root.getObjectByName('shoe_1');
        elemFront.material.color.set(textureData.front);

        elemFooter = root.getObjectByName('shoe_4');
        elemFooter.material.color.set(textureData.footer);

        elemPin = root.getObjectByName('shoe_2');
        elemPin.material.color.set(textureData.pin);

        elemInner = root.getObjectByName('shoe_3');
        elemInner.material.color.set(textureData.inner);

        elemPad = root.getObjectByName('shoe_5');
        elemPad.material.color.set(textureData.pad);

        elemLace = root.getObjectByName('shoe');
        elemLace.material.color.set(textureData.lace);

        elemMiniString = root.getObjectByName('shoe_6');
        elemMiniString.material.color.set(textureData.mini_string);

        let userImageURL = `http://localhost:8080/ShoeStylize_Tomcat/api/pending-order-texture?uuid=${uuid}`
        fetch(userImageURL)
            .then(response => {
                if (response.ok) {
                    let loader = new THREE.TextureLoader();
                    elemFront.material.color.set(0xFFFFFF);
                    $('#d-front-container').css('display', 'none')
                    elemFront.material.map = loader.load(userImageURL);
                    elemFront.material.needsUpdate = true;
                }
            })
    }, function (xhr) {
        //console.log((xhr.loaded / xhr.total * 100) + '% loaded');
    });

    if (light1 == null) {
        light1 = new THREE.PointLight(0xFFFFFF, 1, 100);
        light1.position.set(10, 0, 35);
        scene.add(light1);
    }

    if (light2 == null) {
        light2 = new THREE.PointLight(0xFFFFFF, 1, 100);
        light2.position.set(-10, 0, -35);
        scene.add(light2);
    }

    if (light3 == null) {
        light3 = new THREE.PointLight(0xFFFFFF, 1, 100);
        light3.position.set(0, 35, 0);
        scene.add(light3);
    }

    if (light4 == null) {
        light4 = new THREE.PointLight(0xFFFFFF, 1, 100);
        light4.position.set(0, -35, 0);
        scene.add(light4);
    }

    var render = () => {
        requestAnimationFrame(render);
        renderer.render(scene, camera);
    }

    render()
}

let openModal = () => {
    new bootstrap.Modal($("#modal-view")).toggle()
}

$(document).ready(r => {
    $(".btn-cart-info").click(c => {
        let $target = $(c.target)
        openModal()

        let uuid = $target.attr("data-uuid")

        aget("http://localhost:8080/ShoeStylize_Tomcat/api/pending-order", {
            uuid: uuid
        }, s => {
            $("#form-view--title").text(s.title)
            $("#form-view--customerNote").text(s.note)
            $("#form-view--address1").text(s.address1)
            $("#form-view--address2").text(s.address2)
            $("#form-view--phone1").text(s.phone1)
            $("#form-view--phone2").text(s.phone2)
            $("#form-view--totalPrice").text(`$${s.total_price}`)

            $("#form-view--extras").empty();
            $("#form-view--extras").append(`
                <tr>
                    <td><b>Base item</b></td>
                    <td id="form-view--shoePrice">$${s.shoe_price}</td>
                </tr>
            `)
            s.extras.forEach(ex => {
                $("#form-view--extras").append(`
                <tr>
                    <td>${ex.name}</td>
                    <td>$${ex.price}</td>
                </tr>
            `)
            })

            $("#form-view--referencePrompt").text(s.referencePrompt)
            let textureData = JSON.parse(s.textureData)
            build3DLowTop(textureData, uuid)
        })
    })
})