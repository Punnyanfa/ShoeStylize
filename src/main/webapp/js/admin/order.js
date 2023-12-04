import * as THREE from 'three'
import {GLTFLoader} from 'GLTFLoader'
import {OrbitControls} from 'OrbitControls';

$(document).ready(r => {
    let root, scene, camera, controls, renderer

    let build3DLowTop = (id, width, height, textureData, textureURL) => {

        if (renderer != null) {
            renderer.dispose()
        }

        let canvas = document.querySelector("#modal-content-canvas > canvas")
        scene = new THREE.Scene();
        camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
        camera.position.z = 1.5
        controls = new OrbitControls(camera, canvas);
        renderer = new THREE.WebGLRenderer({
            canvas: canvas
        })

        renderer.setClearColor("#eaeaea");
        renderer.setSize(width, height);
        camera.updateProjectionMatrix();

        const loader = new GLTFLoader();
        let elemFront = null;
        let elemFooter = null;
        let elemLace = null;
        let elemMiniString = null;
        let elemInner = null;
        let elemPin = null;
        let elemPad = null;

        loader.load('http://localhost:8080/ShoeStylize_Tomcat/assets/models/low-tops/shoe.gltf', (gltf) => {
            let root = gltf.scene;
            scene.add(root);

            root.traverse(function (child) {
                if (child instanceof THREE.Mesh) {
                    //  child.material = material;
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

            fetch(textureURL)
                .then(response => {
                    if (response.ok) {
                        let loader = new THREE.TextureLoader();
                        elemFront.material.color.set(0xFFFFFF);
                        elemFront.material.map = loader.load(textureURL);
                        elemFront.material.needsUpdate = true;
                    }
                })

        }, function (xhr) {
            //console.log((xhr.loaded / xhr.total * 100) + '% loaded');
        });

        var light = new THREE.PointLight(0xFFFFFF, 1, 100);
        light.position.set(10, 0, 35);
        scene.add(light);

        var light2 = new THREE.PointLight(0xFFFFFF, 1, 100);
        light2.position.set(-10, 0, -35);
        scene.add(light2);

        var light3 = new THREE.PointLight(0xFFFFFF, 1, 100);
        light3.position.set(0, 35, 0);
        scene.add(light3);

        var light4 = new THREE.PointLight(0xFFFFFF, 1, 100);
        light4.position.set(0, -35, 0);
        scene.add(light4);

        var render = () => {
            requestAnimationFrame(render);
            renderer.render(scene, camera);
        }

        render()
    }

    $('.btn-preview').click(c => {
        let $target = $(c.target)
        let uuid = $target.attr('data-id')
        let textureData = JSON.parse($target.attr('data-textureData'))
        let textureURL = $target.attr('data-textureURL')
        let $modalPreview = $(`#modal-preview`)

        let modal = new bootstrap.Modal($modalPreview, {
            keyboard: false
        })

        let width = window.innerWidth / 2
        let height = innerHeight / 2
        modal.show()

        build3DLowTop(uuid, width, height, textureData, textureURL)
    })
})