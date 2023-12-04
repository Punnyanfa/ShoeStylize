import * as THREE from 'three'
import {GLTFLoader} from 'GLTFLoader'
import {OrbitControls} from 'OrbitControls';

const canvas = document.querySelector(".webgl");
var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
camera.position.z = 1.5
const controls = new OrbitControls(camera, canvas);
var renderer = new THREE.WebGLRenderer({
    canvas: canvas
})

renderer.setClearColor("#FFFFFF");
renderer.setSize(window.innerWidth / 2, window.innerWidth / 5);
camera.updateProjectionMatrix();

let root = null

//Add new geometry
// var geometry = new THREE.BoxGeometry(1, 1, 1)
// var material = new THREE.MeshLambertMaterial({ color: 0xFFCC00 })
// var mesh = new THREE.Mesh(geometry, material)
// scene.add(mesh)

const loader = new GLTFLoader();
let elemFront = null;
let elem2 = null;
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
            //  child.material = material;
        }
    });

    elemFront = root.getObjectByName('shoe_1');
    elemFront.material.color.set(0xFFFFFF);

    elem2 = root.getObjectByName('shoe_4');
    elem2.material.color.set(0xFFFFFF);

    elemPin = root.getObjectByName('shoe_2');
    elemPin.material.color.set(0xFFFFFF);

    elemInner = root.getObjectByName('shoe_3');
    elemInner.material.color.set(0xFFFFFF);

    elemPad = root.getObjectByName('shoe_5');
    elemPad.material.color.set(0xFFFFFF);

    elemLace = root.getObjectByName('shoe');
    elemLace.material.color.set(0xFFFFFF);

    elemMiniString = root.getObjectByName('shoe_6');
    elemMiniString.material.color.set(0xFFFFFF);

    let elemSubPad = root.getObjectByName('shoe_7');
    elemSubPad.material.color.set(0xFFFFFF);


}, function (xhr) {
    //console.log((xhr.loaded / xhr.total * 100) + '% loaded');
});

//Light
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

render();

$("#color-front").change(function () {
    elemFront.material.color.set($("#color-front").val());
});

$("#color-footer").change(function () {
    elem2.material.color.set($("#color-footer").val());
});

$("#color-lace").change(function () {
    elemLace.material.color.set($("#color-lace").val());
});

$("#color-inner").change(function () {
    elemInner.material.color.set($("#color-inner").val());
});

$("#color-pin").change(function () {
    elemPin.material.color.set($("#color-pin").val());
});

$("#color-pad").change(function () {
    elemPad.material.color.set($("#color-pad").val());
});

$("#color-mini-string").change(function () {
    elemMiniString.material.color.set($("#color-mini-string").val());
});

$(document).ready(d => {
    $(document).on('change', '#textureUpload', function () {

        const input = this;
        const file = input.files[0];

        if (file) {
            let userImageURL = URL.createObjectURL(file);
            var loader = new THREE.TextureLoader();
            elemFront.material.color.set(0xFFFFFF);
            $('#d-front-container').css('display', 'none')
            // elemFront.material.map = texture
            elemFront.material.map = loader.load(userImageURL);
            elemFront.material.needsUpdate = true;
        }
    });
})