function isNumeric(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

var initialTable;

function createTable(size) {
    document.getElementById("defaultColor").value = "cyan";
	var resultElement = document.getElementById("result");
	
	document.getElementById("update").hidden = true;

	size = parseFloat(size);
	if (size < 1 || size > 50) {
		resultElement.innerHTML = "<b>Ошибка!</b> C такими размерами нельзя построить фигуру<br>";
		return;
	}

    if (!isNumeric(size)) {
		resultElement.innerHTML = "<b>Ошибка!</b> Проверьте введёное значение<br>";
		return;
	}
	
	resultElement.innerHTML = "";
	document.getElementById("update").hidden = false;

	var table = document.createElement("table");
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);

	for (var y = 0; y < size; y++) {
		let row = document.createElement("tr");
		for (var x = 0; x < size; x++) {
			let cell = document.createElement("td");
            let cellText;
            if (y + 1 <= Math.ceil(size / 2)) {
                if (x == y || x == size - 1 -  y) {
                    cellText = document.createTextNode(y + 1);
                }
            }
            if (x == 0 || x == size - 1) {
                cellText = document.createTextNode(y + 1);
            }
			cell.width = "25px";
			cell.align = "center";
			cell.bgColor = cellText.length ? "cyan" : "red";
			cell.appendChild(cellText);
			row.appendChild(cell);
		}
		tbody.appendChild(row);
	}
	resultElement.appendChild(table);
    initialTable = table.cloneNode(true);
}

function initTable() {
    var resultElement = document.getElementById("result");
    resultElement.replaceChild(initialTable.cloneNode(true), resultElement.getElementsByTagName("table")[0]);
}

function isValidColor(color) {
    var span = document.createElement("span");
    span.style.color = color;
    return span.style.color !== "";
}

function updateTable(color) {
    if (!isValidColor(color)) {
        let resultElement = document.getElementById("result");
        resultElement.innerHTML = "<b>Ошибка!</b> Цвет введен неверно!<br>";
        return;
    }
    initTable();

    var defaultColor = document.getElementById("defaultColor").value;
    var rows = document.getElementById("result").getElementsByTagName("tr");
    for (var i = 0; i < rows.length; i++) {
        var cells = rows[i].getElementsByTagName("td");
        for (var j = 0; j < cells.length; j++) {
            if (cells[j].bgColor === defaultColor) {
                cells[j].bgColor = color;
            }
        }
    }
    document.getElementById("defaultColor").value = color;
}

function processTable(mode, color) {
    if (!isValidColor(color)) {
        let resultElement = document.getElementById("result");
        resultElement.innerHTML = "<b>Ошибка!</b> Цвет введен неверно!<br>";
        return;
    }
    initTable();

    var rows = document.getElementById("result").getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var cells = rows[i].getElementsByTagName("td");
        var result = 0;
        var counter = 0;
        for (var j = 0; j < cells.length; j++) {
            var cellValue = parseFloat(cells[j].textContent);
            if (isNumeric(cellValue)) {
                result += cellValue;
                counter++;
            }
        }
        
        var result = mode == "sum" ? result : result / counter;
        for (var k = 0; k < cells.length; k++) {
            if (cells[k].bgColor === color) {
                cells[k].textContent = result;
            }
        }
    }
}

function removeRowsWithRedCellsLimit(limit) {
    initTable();

    var rows = document.getElementById("result").getElementsByTagName("tr");
    
    for (var i = 0; i < rows.length; i++) {
        var redCellCounter = 0;
        var cells = rows[i].getElementsByTagName("td");
        for (var j = 0; j < cells.length; j++) {
            if (cells[j].bgColor === "red") {
                redCellCounter++;
            }
        }
        if (redCellCounter > parseInt(limit)) {
            rows[i].remove();
        }
    }
}

function init() {
	var resultTable = document.createElement("div");
	resultTable.id = "result";
	document.body.appendChild(resultTable);
	document.getElementById("update").hidden = true;
}