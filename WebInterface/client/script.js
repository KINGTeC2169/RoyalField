var games, rankings;

// Load the csv
$(document).ready(() => {
  loadRankings();
  setInterval(loadRankings, 10000);
});

function loadRankings() {
  $.ajax({
    type: "GET",
    url: "rankings.csv",
    dataType: "text",
    success: (data) => {
      setRankings(
        document.getElementById("rankings"),
        parseCSV(data)
      );
    }
   });
}

function setRankings(table, rankings){
  let ref = {html:""};

  let addData = (val) => {
    ref.html += "<td>" + val + "</td>";
  }
  let openEntry = () => {
    ref.html += "<tr>"
  }
  let closeEntry = () => {
    ref.html += "</tr>\n";
  }

  openEntry();
  addData("Rank");
  addData("Team #");
  addData("Team Name");
  addData("W");
  addData("T");
  addData("L");
  addData("Ranking Points");
  closeEntry();

  for(var i in rankings) {
    let team = rankings[i];
    openEntry();
    addData(team.rank);
    addData(team.number);
    addData(team.name);
    addData(team.w);
    addData(team.t);
    addData(team.l);
    addData(team.points);
    closeEntry();
  }

  table.innerHTML = ref.html;

  rankings.sort((a, b) => {
    return a.number - b.number;
  })

  $.ajax({
    type: "GET",
    url: "games.csv",
    dataType: "text",
    success: (data) => {
      setGames(
        document.getElementById("games"),
        rankings,
        parseCSV(data)
      );
    }
   });
}

function setGames(table, teams, games){
  let ref = {html:""};

  let addAlliance = (color, name1, name2) => {
    ref.html += "<td class='" + color + "'>" + name1 + " <br />-and-<br/> " + name2 + "</td>";
  }
  let addVersus = () => {
    ref.html += "<td class='versus'>VS</td>"
  }
  let addScore = (redscore, bluescore) => {
    if(redscore > bluescore) {
      color = "red";
    } else if(bluescore > redscore) {
      color = "blue";
    } else {
      color = "orange";
    }
    ref.html += "<td class=" + color + ">Final Score<br />Blue:" + bluescore + "<br />Red:" + redscore + "</td>"
  }
  let openEntry = () => {
    ref.html += "<tr>"
  }
  let closeEntry = () => {
    ref.html += "</tr>\n";
  }

  for(var i in games) {
    let game = games[i];
    openEntry();
    addAlliance("red", teams[game.r1-1].name, teams[game.r2-1].name);
    addVersus();
    addAlliance("blue", teams[game.b1-1].name, teams[game.b2-1].name);
    addScore(game.redscore, game.bluescore);
    closeEntry();
  }

  table.innerHTML = ref.html;
}

// Turn CSV text into an array of objects
function parseCSV(text) {
  let table = [];
  let lines = text.split(/\r\n|\n/);
  let headings = lines[0].split(',');

  // Format the headings
  for(i in headings)
    headings[i] = headings[i].replace(" ", "").toLowerCase();

  for (var i = 1; i < lines.length; i++) {
    if(lines[i] === "") continue; // Don't include blank lines

    let entries = lines[i].split(',');
    let obj = {};
    for(var j in headings) {
      if(!isNaN(entries[j])) {
        entries[j] = Number(entries[j]);
      }
      obj[headings[j]] = entries[j];
    }
    table.push(obj);
  }

  return table;
}
