function isNumeric(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

function filterVector(vector) {
    var vectorPlus = vector.filter(num => num > 0);
    var vectorMinus = vector.filter(num => num < 0);
    return [vectorPlus, vectorMinus];
}

function filterMatrix(matrix) {
    var filteredMatrix = matrix.map(vector => filterVector(vector));
    var matrixPlus = filteredMatrix.map(vector => vector[0]);
    var matrixMinus = filteredMatrix.map(vector => vector[1]);
    return [matrixPlus, matrixMinus];
}

function findMinAndMaxVectors(matrixPlus, matrixMinus) {
    var vectorMax = matrixMinus.map(vectors => Math.max(...vectors));
    var vectorMin = matrixPlus.map(vectors => Math.min(...vectors));
    return [vectorMax, vectorMin];
}

function generateMatrix(minValue, maxValue, matrixSize) {
    return Array.from(
        {length: matrixSize}
        , () => Array.from({length: matrixSize}, () => (Math.random() * (maxValue - minValue + 1) + minValue).toFixed(2))
    );
}

function calculate() {
    var matrixSize = parseInt(document.getElementById('matrixSize').value);
    var minValue = parseFloat(document.getElementById('minValue').value);
    var maxValue = parseFloat(document.getElementById('maxValue').value);

    if (!isNumeric(matrixSize) || !isNumeric(minValue) || !isNumeric(maxValue)) {
        let output = document.getElementById("output");
        output.innerHTML = "<b>Ошибка!</b> Неверные числовые значения";
        return;
    }

    if (matrixSize < 2) {
        let output = document.getElementById("output");
        output.innerHTML = "<b>Ошибка!</b> Неверное значение размера матрицы";
        return;
    }

    if (minValue > maxValue) {
        [minValue, maxValue] = [maxValue, minValue];
    }

    var matrix = generateMatrix(minValue, maxValue, matrixSize);

    var [matrixPlus, matrixMinus] = filterMatrix(matrix);
    var [vectorMax, vectorMin] = findMinAndMaxVectors(matrixPlus, matrixMinus);

    var output = document.getElementById('output');
    output.innerHTML = `
        Исходная матрица:<br>
        <pre>${matrix.map(row => row.join('\t')).join('\n')}</pre>
        Матрица положительных элементов:<br>
        <pre>${matrixPlus.map(row => row.join('\t')).join('\n')}</pre>
        Матрица отрицательных элементов:<br>
        <pre>${matrixMinus.map(row => row.join('\t')).join('\n')}</pre>
        Вектор максимальных элементов:<br>
        <pre>${vectorMax.join('\t')}</pre>
        Вектор минимальных элементов:<br>
        <pre>${vectorMin.join('\t')}</pre>
    `;
}
