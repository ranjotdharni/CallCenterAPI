const eventSource = new EventSource('http://localhost:8080/entrance');
var data;

const listContainer = document.getElementById('listcontainer');
const activateBtn = document.getElementById('activatebtn');
const testBtn = document.getElementById('testbtn');
const states = ['blue', 'green', 'orange'];
var dots = document.getElementsByClassName('listitem');

activateBtn.addEventListener('click', reset);
testBtn.addEventListener('click', update);
eventSource.addEventListener('message', saveEmployee)

//eventSource.addEventListener('message', saveEmployee);
document.onload = fillData;
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

function update (event)
{
    event.preventDefault();

    for (var i = 0; i < data.length; i++)
    {
       if (dots[i].style.backgroundColor == 'rgb(105, 105, 105)')
       {
            dots[i].classList.add('neon');
       }

       dots[i].style.backgroundColor = data[i].status;
    }

    //var id = Math.floor(Math.random() * 10000);
    //
    //if (dots[id].style.backgroundColor == 'rgb(105, 105, 105)')
    //{
    //    dots[id].classList.add("neon");
    //}
    //
    //dots[id].style.backgroundColor = states[Math.floor(Math.random() * 3)];
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

    fillData();
}

function getData()
{
    return fetch("http://localhost:8080/gate").then(function (responseBody)
    {
        return responseBody.json();

    });
}

async function fillData()
{
    data = await getData();
}