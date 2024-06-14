const tableBody = document.querySelector("#odontologosTable tbody");
const formulario = document.querySelector('#update_odontologo_form');
const divFormulario = document.querySelector('#div_odontologo_updating');


function fetchOdontologos() {
  fetch(`http://localhost:8080/odontologo`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      tableBody.innerHTML = "";

      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
          <td>${odontologo.id}</td>
          <td>${odontologo.nombre}</td>
          <td>${odontologo.apellido}</td>
          <td>${odontologo.nroMatricula}</td>
          <td>
            <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.nroMatricula}')">Modificar</button>
            <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
          </td>
        `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

function editOdontologo(id, nombre, apellido, nroMatricula) {
  document.querySelector('#odontologo_id').value = id;
  document.querySelector('#nombre').value = nombre;
  document.querySelector('#apellido').value = apellido;
  document.querySelector('#matricula').value = nroMatricula;

  divFormulario.classList.remove('d-none');
  divFormulario.classList.add('d-block');
}



formulario.addEventListener('submit', function (event) {
  event.preventDefault();

  const formData = {
    id: document.querySelector('#odontologo_id').value,
    nombre: document.querySelector('#nombre').value,
    apellido: document.querySelector('#apellido').value,
    nroMatricula: document.querySelector('#matricula').value
  };

  fetch(`http://localhost:8080/odontologo`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(formData)
  })
    .then(response => response.json())
    .then(data => {
      console.log("Odontólogo modificado:", data);
      divFormulario.classList.remove('d-block');
      divFormulario.classList.add('d-none');
      fetchOdontologos(); // Actualizar la tabla después de modificar
    })
    .catch(error => {
      console.error("Error updating odontologo:", error);
    });
});

function deleteOdontologo(id) {
  fetch(`http://localhost:8080/odontologo/${id}`, {
    method: 'DELETE'
  })
    .then(response => {
      if (response.ok) {
        console.log(`Odontólogo con ID ${id} eliminado.`);
        fetchOdontologos(); // Actualizar la tabla después de eliminar
      } else {
        console.error(`Error al eliminar el odontólogo con ID ${id}`);
      }
    })
    .catch(error => {
      console.error("Error deleting odontologo:", error);
    });
}

fetchOdontologos();