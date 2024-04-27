var inventoryList = [];

function generateItemName(number) {
    return "Объект " + (number + 1);
}

function generateDate(start, end) {
    return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
}

function generateInventoryNumber() {
    return Math.floor(Math.random() * 10000) + 1000;
}

function generateQuantity() {
    return Math.floor(Math.random() * 10) + 1;
}

function generateLocation() {
    return {
        building: Math.floor(Math.random() * 5) + 1,
        floor: Math.floor(Math.random() * 10) + 1,
        room: Math.floor(Math.random() * 20) + 1
    };
}

function isNumeric(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

function generateInventoryList(n) {
    if (!isNumeric(n)) {
        var outputDiv = document.getElementById("output");
        outputDiv.innerHTML = "<b>Ошибка!</b>Неверный размер";
        return;
    }

    document.getElementById("settings").hidden = false;

    inventoryList = [];

    for (var i = 0; i < parseInt(n); i++) {
        inventoryList.push({
            itemName: generateItemName(i),
            inventoryNumber: generateInventoryNumber(),
            acquisitionDate: generateDate(new Date(2023, 0, 1), new Date()),
            quantity: generateQuantity(),
            location: generateLocation()
        });
    }

    print(inventoryList);
}

function removeCurrentYearAcquisition() {
    var currentDate = new Date();
    inventoryList.forEach(item => {
        if (item.acquisitionDate.getFullYear() == currentDate.getFullYear()) {
            delete item.acquisitionDate;
        }
    })
    print(inventoryList);
}


function addServiceLifeInfo() {
    var currentDate = new Date();
    inventoryList.forEach(item => {
        if (item.acquisitionDate) {
            item.serviceLife = currentDate.getFullYear() - item.acquisitionDate.getFullYear();
        }
    });

    print(inventoryList);
}

function filterList(value) {
    if (!isNumeric(value)) {
        var outputDiv = document.getElementById("output");
        outputDiv.innerHTML = "<b>Ошибка!</b>Неверный размер";
        return;
    }

    var selectedRadioButton = document.querySelector('input[name="filter"]:checked');
    if (selectedRadioButton.id === "floorFlag") {
        var filteredList = inventoryList.filter(item => item.location.floor === parseFloat(value));
    } else if (selectedRadioButton.id === "buildingFlag") {
        var filteredList = inventoryList.filter(item => item.location.building === parseFloat(value));
    } else {
        var filteredList = inventoryList.filter(item => item.serviceLife === parseFloat(value));
    }
    
    print(filteredList);
}

function serializeList() {
    var serializedData = JSON.stringify(inventoryList);
    var output = document.getElementById("serializedList");
    output.innerHTML = serializedData;
}

function deserializeList(serializedData) {
    console.log(serializedData);
    inventoryList = JSON.parse(serializedData, (key, value) => {
        if (key === "acquisitionDate") {
            return new Date(value);
        }
        return value;
    });
    document.getElementById("settings").hidden = false;
    print(inventoryList);
}

function print(list) {
    var outputDiv = document.getElementById("output");
    outputDiv.innerHTML = "Список объектов<br>";
    if (list == null) {
        list = inventoryList;
    }
    list.forEach(item => {
        var itemDiv = document.createElement("div");
        itemDiv.innerHTML = `
            <p><strong>Наименования:</strong> ${item.itemName}</p>
            ${item.inventoryNumber ? `<p><strong>Инвентарный номер:</strong> ${item.inventoryNumber}</p>` : ''}
            ${item.acquisitionDate ? `<p><strong>Дата принятия:</strong> ${item.acquisitionDate.toDateString()}</p>` : ''}
            ${item.quantity ? `<p><strong>Количество:</strong> ${item.quantity}</p>` : ''}
            ${item.location ? `<p><strong>Адрес:</strong> Здание ${item.location.building}, Этаж ${item.location.floor}, Квартира ${item.location.room}</p>` : ''}
            ${item.serviceLife ? `<p><strong>Срок службы:</strong> ${item.serviceLife} лет</p><br>` : '<br>'}  
        `;
        outputDiv.appendChild(itemDiv);
    });
}