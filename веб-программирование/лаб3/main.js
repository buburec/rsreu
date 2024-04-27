function factorial(n) {
    if ((n == 0) || (n == 1)) {
        return 1;
    } 
    return n * factorial(n - 1);
}

function isNumericArgs(...args) {
    for (var i = 0; i < args.length; i++) {
        if (isNaN(args[i]) || !isFinite(args[i])) {
            return false;
        }
    }
    return true;
}

function isNonSignIntegerArgs(...args) {
    for (var i = 0; i < args.length; i++) {
        if ((6 < args[i]  || args[i] < 2) || !Number.isInteger(args[i]) || Math.sign(args[i]) == -1) {
            return false;
        }
    }
    return true;
}

function leftFunction(x, y, n) {
    return 1 + y * Math.log(Math.pow(x, n));
}

function rightFunction(x, y) {
    return (x + 1) / (3 * y);
}

function centreFunction(x, y, n) {
    return (7 * x + Math.pow(y, n - 1)) / (2 * factorial(n) + y);
}

function summaryLeftFunction(nm, x, y) {
    var summary = 0;
    for (var n = 1; n <= nm; n++) {
        summary += leftFunction(x, y, n);
    }
    return summary;
}

function summaryRightFunction(nm, x, y) {
    var summary = 0;
    for (var n = 1; n <= nm; n++) {
        summary += centreFunction(x, y, n);
    }
    return rightFunction(x, y) * summary;
}

function tabulateFunction() {
    var resultTable = document.getElementById("resultTable");
    var xStart = parseFloat(document.getElementById("xStart").value);
    var xEnd = parseFloat(document.getElementById("xEnd").value);
    var xStep = parseFloat(document.getElementById("xStep").value);
    var yStart = parseFloat(document.getElementById("yStart").value);
    var yEnd = parseFloat(document.getElementById("yEnd").value);
    var yStep = parseFloat(document.getElementById("yStep").value);
    var nm1 = parseFloat(document.getElementById("nm1").value);
    var nm2 = parseFloat(document.getElementById("nm2").value);

    if (!isNumericArgs(xStart, xEnd, xStep, yStart, yEnd, yStep, nm1, nm2)) {
        resultTable.innerHTML = "Неверные исходные данные!";
        return;
    }

    if (!isNonSignIntegerArgs(nm1, nm2)) {
        resultTable.innerHTML = "Ошибка! nm1 и/или nm2 не лежат в диапазоне [2;6] или не являются целочисленными или отрицательные<br>";
        return;
    }

    if ((xStart > xEnd && xStep > 0) || (xStart < xEnd && xStep < 0) || xStart == xEnd || xStep == 0) {
        resultTable.innerHTML = "Ошибка! C такими значениями нельзя табулировать функцию по X<br>";
        return;
    }

    if ((yStart > yEnd && yStep > 0) || (yStart < yEnd && yStep < 0) || yStart == yEnd || yStep == 0) {
        resultTable.innerHTML = "Ошибка! C такими значениями нельзя табулировать функцию по Y<br>";
        return;
    }

    resultTable.innerHTML = "Результаты табуляции от " + xStart + " до " + xEnd + " c шагом " + xStep + " по X и<br>от " + yStart + " до " + yEnd + " c шагом " + yStep + "по Y:<br>";

	var tempRows = "<table width='600'>"; 
    tempRows += "<tr><td>X</td><td>Y</td><td>Result</td></tr>";
    var minResult, maxResult;
    minResult = maxResult = summaryLeftFunction(nm1, xStart, yStart) + summaryRightFunction(nm2, xStart, yStart);
    for (var x = xStart; xStart < xEnd ? x < xEnd + xStep * 0.5: x > xEnd + xStep * 0.5; x += xStep) {
        for (var y = yStart; yStart < yEnd ? y < yEnd + yStep * 0.5: y > yEnd + yStep * 0.5; y += yStep) {
            var result = summaryLeftFunction(nm1, x, y) + summaryRightFunction(nm2, x, y);
            maxResult = result > maxResult ? result : maxResult;
            minResult = result < minResult ? result : minResult;
            var row = "<tr><td>" + x + "</td><td>" + y + "</td><td>" + result + "</td></tr>";
            tempRows += row;
        }
    }
    tempRows += "</table>";
	resultTable.innerHTML += tempRows;
    resultTable.innerHTML += "Минимальное значение табулирования: " + minResult + ";<br>Максимальное значение табулирования: " + maxResult;
}