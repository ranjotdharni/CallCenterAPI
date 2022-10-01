const eventSource = new EventSource('http://localhost:8080/entrance');
const listContainer = document.getElementById('listcontainer');

eventSource.addEventListener('message', saveEmployee);

function saveEmployee (event)
{   

    event.preventDefault();

    if (event.data != 'establish_success')
    {
        alert(event.data);
        const listDiv = document.createElement('div');
        listDiv.innerHTML = '<div>Wassup</div>';
        listDiv.classList.add('listitem');

        listContainer.appendChild(listDiv);
    }
}