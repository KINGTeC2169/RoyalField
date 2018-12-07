var games, rankings;

// Load the csv
$(document).ready(() => {
  $.ajax({
    type: "GET",
    url: "rankings.csv",
    dataType: "text",
    success: (data) => {
      games = parseCSV(data);
    }
   });

   $.ajax({
     type: "GET",
     url: "games.csv",
     dataType: "text",
     success: (data) => {
       rankings = parseCSV(data);
     }
    });
});

// Turn CSV text into an array of objects
function parseCSV(allText) {
  let table = [];
  let lines = allText.split(/\r\n|\n/);
  let headings = lines[0].split(',');

  for (var i = 1; i < lines.length; i++) {
    if(lines[i] === "") continue;

    let entries = lines[i].split(',');
    let obj = {};
    for(var j in headings) {
      if(!isNaN(entries[j]))
        entries[j] = Number(entries[j]);
      obj[headings[j]] = entries[j];
    }
    table.push(obj);
  }

  return table;
}
