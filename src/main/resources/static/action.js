const eventSource = new EventSource('http://localhost:8080/entrance');
const listContainer = document.getElementById('listcontainer');
const activateBtn = document.getElementById('activatebtn');
const testBtn = document.getElementById('testbtn');
const states = ['blue', 'green', 'orange'];
var dots = document.getElementsByClassName('listitem');

activateBtn.addEventListener('click', reset);
testBtn.addEventListener('click', random);
eventSource.addEventListener('message', saveEmployee)

//eventSource.addEventListener('message', saveEmployee);
window.onload = loadGrid;

function reset (event)
{
    event.preventDefault();

    for (var i = 0; i < dots.length; i++)
    {
        if (dots[i].style.backgroundColor != 'rgb(105, 105, 105)')
        {
            dots[i].style.backgroundColor = 'rgb(105, 105, 105)';
            dots[i].classList.remove('neon');
        }
    }
}

function random (event)
{
    event.preventDefault();

    var id = Math.floor(Math.random() * 10000);

    if (dots[id].style.backgroundColor == 'rgb(105, 105, 105)')
    {
        dots[id].classList.add("neon");
    }

    dots[id].style.backgroundColor = states[Math.floor(Math.random() * 3)];
}

function saveEmployee (event)
{   

    event.preventDefault();
    var id = parseInt(event.data);
    var state = states[Math.floor(Math.random() * 3)];
    
    if (dots[id].style.backgroundColor != state && dots[id].style.backgroundColor != 'rgb(105, 105, 105)')
    {
        dots[id].style.backgroundColor = state;
    }
    else if (dots[id].style.backgroundColor != state)
    {
        dots[id].style.backgroundColor = state;
        dots[id].classList.add('neon');
    }
    else
    {
        dots[id].style.backgroundColor = 'rgb(105, 105, 105)';
        dots[id].classList.remove('neon');
    }
    
    
}

function loadGrid(event)
{
    event.preventDefault();

    for (let i = 0; i < 10000; i++)
    {
        const listDiv = document.createElement('div');
        listDiv.classList.add('listitem');
        listDiv.style.backgroundColor = 'rgb(105, 105, 105)';
        listContainer.appendChild(listDiv);
    }
}