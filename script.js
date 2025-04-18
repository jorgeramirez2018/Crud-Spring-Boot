const tablaProductos = document.getElementById("tablaProductos");
const nombre = document.getElementById("nombre");
const categoria = document.getElementById("categoria");
const valor = document.getElementById("valor");
const btnGuardar = document.getElementById("btnGuardar");
let tituloModal = document.getElementById("tituloModal");
let idProductoModificar = 0;

// Acciones Guardar Producto
btnGuardar.addEventListener("click", () => {
    if (tituloModal.innerText === "Modificar Producto") {
        crearProducto(idProductoModificar);
    } else if (tituloModal.innerText === "Crear Producto") {
        crearProducto(0);
    }
});

// Función para modificar producto
function modificarProducto(id) {
    tituloModal.innerText = "Modificar Producto";
    modal.style.display = "block";
    idProductoModificar = id;

    let producto = listaProductos.find((p) => p.id === id);
    if (producto) {
        nombre.value = producto.nombre;
        categoria.value = producto.categoria;
        valor.value = producto.valor;
    } else {
        alert("Producto no encontrado");
    }
}

// Crear o actualizar producto
async function crearProducto(id) {
    let productoGuardar = {
        nombre: nombre.value,
        categoria: categoria.value,
        valor: parseFloat(valor.value)
    };

    let metodo = id === 0 ? "POST" : "PUT";
    let url = id === 0 ? "http://localhost:8080/producto" : `http://localhost:8080/producto/${id}`;

    await fetch(url, {
        method: metodo,
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(productoGuardar)
    })
    .then((response) => {
        if (!response.ok) {
            throw new Error("Error en la solicitud");
        }
        return response.json();
    })
    .then((producto) => {
        console.log(producto);
        alert("Producto guardado correctamente");
        modal.style.display = "none";
        resetModal();
        ObtenerProductos(); 
    })
    .catch((err) => {
        console.log(err);
        alert("Lo siento, no pudimos guardar el producto");
    });
}

// Obtener productos
let ObtenerProductos = () => {
    fetch("http://localhost:8080/producto")
        .then((response) => response.json())
        .then((productos) => {
            listaProductos = productos;
            tablaProductos.innerHTML = "";
            productos.forEach((producto) => {
                tablaProductos.innerHTML += `
                    <tr>
                        <th scope="row">${producto.id}</th>
                        <td>${producto.nombre}</td>
                        <td>${producto.categoria}</td>
                        <td>${producto.valor}</td>
                        <td>
                            <input type="button" class="btn btn-info" value="Editar" onclick="modificarProducto(${producto.id})" />
                            <input type="button" class="btn btn-danger" value="Eliminar" onclick="eliminarProducto(${producto.id})" />
                        </td>
                    </tr>
                `;
            });
        })
        .catch((err) => {
            console.log(err);
            alert("No pudimos obtener los productos");
        });
};

ObtenerProductos();

// Reset Modal
function resetModal() {
    nombre.value = "";
    categoria.value = "";
    valor.value = "";
    idProductoModificar = 0;
}

// Modal 
var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function () {
    tituloModal.innerText = "Crear Producto";
    modal.style.display = "block";
};
span.onclick = function () {
    modal.style.display = "none";
    resetModal();
};
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
        resetModal();
    }
};

// Eliminar producto
function eliminarProducto(id) {
    if (confirm("¿Estás seguro de que deseas eliminar este producto?")) {
        fetch(`http://localhost:8080/producto/${id}`, {
            method: "DELETE",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Error al eliminar el producto");
            }
            alert("Producto eliminado correctamente");
            ObtenerProductos();
        })
        .catch(() => {
            alert("No pudimos eliminar el producto");
        });
    }
}
