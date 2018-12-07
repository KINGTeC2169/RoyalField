var games, rankings;

// Load the csv
$(document).ready(() => {
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

   $.ajax({
     type: "GET",
     url: "games.csv",
     dataType: "text",
     success: (data) => {
       setGames(
         document.getElementById("games"),
         parseCSV(data)
       );
     }
    });
});

function setRankings(table, rankings){
  let ref = {html:""};

  let addEntry = (val) => {
    ref.html += "<td>" + val + "</td>";
  }
  let openEntry = () => {
    ref.html += "<tr>"
  }
  let closeEntry = () => {
    ref.html += "</tr>\n";
  }

  openEntry();
  addEntry("Rank");
  addEntry("Team #");
  addEntry("Team Name");
  addEntry("W");
  addEntry("T");
  addEntry("L");
  addEntry("Ranking Points");
  closeEntry();

  for(var i in rankings) {
    let team = rankings[i];
    openEntry();
    addEntry(team.rank);
    addEntry(team.number);
    addEntry(team.name);
    addEntry(team.w);
    addEntry(team.t);
    addEntry(team.l);
    addEntry(team.points);
    closeEntry();
  }

  table.innerHTML += ref.html;
}

function setGames(table, games){
  let ref = {html:""};

  let addEntry = (val) => {
    ref.html += "<td>" + val + "</td>";
  }
  let openEntry = () => {
    ref.html += "<tr>"
  }
  let closeEntry = () => {
    ref.html += "</tr>\n";
  }

  openEntry();
  addEntry("Red Alliance");
  addEntry("Blue Alliance");
  addEntry("Red Score");
  addEntry("Blue Score");
  closeEntry();

  for(var i in games) {
    let team = games[i];
    openEntry();
    addEntry(team.r1 + " and " + team.r2);
    addEntry(team.b1 + " and " + team.b2);
    addEntry(team.redscore);
    addEntry(team.bluescore);
    closeEntry();
  }

  table.innerHTML += ref.html;
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
