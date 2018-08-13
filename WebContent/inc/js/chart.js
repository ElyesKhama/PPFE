var chartLine = null;
var chartPie = null;

function testAjax() {
	var valueSelected = document.getElementById("selectDate").value;

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {

			var json = JSON.parse(this.responseText);
			updateTable(json.listWarnings);
			if (valueSelected == "Today") {
				if (chartLine != null) {
					chartLine.destroy();
					chartPie.destroy();
				}
			} else {
				updateChartLine(json);
				updateChartPie(json.listWarnings);
			}
			document.getElementById("countWarningsDisplay").innerHTML = json.listWarnings.length;
		}
	};
	xhttp.open("GET", "ajax?dateDay=" + valueSelected, true);
	xhttp.send();
}

function updateChartLine(json) {
	var ctx = document.getElementById("myChartLine").getContext('2d');
	chartLine = new Chart(ctx, {
		type : 'line',
		data : {
			labels : json.listDayWarnings.reverse(),
			datasets : [ {
				label : "Warnings",
				data : json.listCountWarnings.reverse(),
				fill : false,
				borderColor : 'rgba(0,0,255,1)'
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						stepSize : 1
					}
				} ]
			}
		}
	});
}

function updateTable(arrayWarning) {
	var nbTr = document.getElementsByTagName("tr").length;

	if (nbTr == 0 && arrayWarning.length != 0) {
		fillHeadTable();
	}

	if (arrayWarning.length != 0) {
		removeBodyRows();
		fillBodyTable(arrayWarning);
	} else if (nbTr > 0) { // not the first time
		removeHeadRow();
		removeBodyRows();
	}
}

function removeBodyRows() {
	var bodyTable = document.getElementById("bodyTableWarnings");

	// delete all rows in the table
	var nbRows = document.getElementsByTagName("tr").length;
	for (var i = 0; i < nbRows; i++) {
		bodyTable.deleteRow(-1);
	}
}

function fillBodyTable(arrayWarning) {
	var bodyTable = document.getElementById("bodyTableWarnings");

	var line;
	for (var i = 0; i < arrayWarning.length; i++) {
		var line = bodyTable.insertRow(-1); // add 1 line at the end
		var col0 = line.insertCell(0);
		var col1 = line.insertCell(1);
		var col2 = line.insertCell(2);
		col0.innerHTML += arrayWarning[i].id;
		col1.innerHTML += arrayWarning[i].voucherType;
		col2.innerHTML += arrayWarning[i].difference;
	}
}

function fillHeadTable() {
	var bodyHead = document.getElementById("headTableWarnings");
	var line = bodyHead.insertRow(-1);
	var col0 = line.insertCell(0);
	var col1 = line.insertCell(1);
	var col2 = line.insertCell(2);
	col0.innerHTML += "#";
	col1.innerHTML += "Voucher Type"
	col2.innerHTML += "Difference"
}

function removeHeadRow() {
	var bodyHead = document.getElementById("headTableWarnings");
	bodyHead.deleteRow(-1);
}