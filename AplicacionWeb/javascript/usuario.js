function myFunction() {
    document.getElementById("demo").innerHTML = "Paragraph changed.";
}

//function createNode(element) {
//    return document.createElement(element);
//}
//
//function append(parent, el) {
//  return parent.appendChild(el);
//}
//
//const ul = document.getElementById('authors');
//const url = 'http://localhost:8080/JavaAPI/rest/usuario';
//
//fetch(url, {
//    mode : 'no-cors'
//}).then((resp) => resp.json())
//.then(function(data) {
//    console.log(data);
//})
//.catch(function(error) {
//  console.log(error);
//});

// Defining async function
//async function fetchTest() {
//    
//    fetch('http://localhost:8080/JavaAPI/rest/usuario', {
//        mode : 'no-cors'
//    }).then((resp) => resp.text())
//    .then(function(data) {
//        console.log(data);
//    })
//    .catch(function(error) {
//    console.log(error);
//    });
//    document.getElementById('result').innerHTML = resp;
//}
//
//(async() => {
//    await fetchTest();
//})();



getText("fetch_info.txt");

async function getText() {
    let x = await fetch('http://localhost:8080/JavaAPI/rest/usuario', {
        mode : 'no-cors'});
    let y = await x.text();
    document.getElementById("demo").innerHTML = y;
}
