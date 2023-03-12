const audios = document.querySelectorAll("audio");
const tbody = document.querySelector("tbody")
const sampleRow = document.querySelector("#sampleRow")

function addRow(name, author, play) {
    let row = sampleRow.cloneNode(true);
    row.removeAttribute("id");
    row.removeAttribute("aria-hidden");
    row.querySelector("#name").innerText = name;
    row.querySelector("#author").innerText = author;
    row.querySelector("#play").querySelector("audio").src = play;
    tbody.appendChild(row);
}

function getSongs() {
    let songs = [];
    fetch("http://host.docker.internal:8081/songs", {
        method: "GET",
        mode: "cors"
    }).then(response => response.json()
    ).then(response => songs = JSON.parse(response));
}

function pauseOtherAudios({target}) {
    for (const audio of audios) {
        if (audio !== target) {
            audio.pause();
        }
    }
}

for (const audio of audios) {
    audio.addEventListener("play", pauseOtherAudios);
}

document.querySelector("#addForm").addEventListener("submit", (e) => {
    const formData = new FormData(e.target);
    fetch("http://host.docker.internal:8081/songs", {
        method: "POST",
        body: formData,
        mode: "cors"
    }).then(response => response.json()
    ).then(response => console.log(JSON.stringify(response)));
});