//TODO: Singleton Pattern see how to store the json

var chartLine = null;
var chartPie = null;
var nbMaxVoucher = 10;

function testAjax() {
	var valueSelectedDate = document.getElementById("selectDate").value;
	var valueSelectFilterPie = document.getElementById("selectFilterChartPie").value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var json = JSON.parse(this.responseText);
			updateTable(json.listWarnings);
			manageCharts(json, valueSelectedDate, valueSelectFilterPie);
			document.getElementById("countWarningsDisplay").innerHTML = json.listWarnings.length;
		}
	};
	xhttp.open("GET", "ajax?dateDay=" + valueSelectedDate, true);
	xhttp.send();
}

function manageCharts(json, valueSelectedDate, valueSelectFilterPie){
    
    var listTest1 = json.listDayWarnings.reverse();
    var listTest2 = json.listCountWarnings.reverse();

    if( chartLine == null){
	createChartLine(listTest1,listTest2);
	var listPie = createListLabelsCounts(json.listWarnings, valueSelectFilterPie);
	createChartPie(listPie[0],listPie[1]);
    }	

    if (valueSelectedDate == "Today") {
	document.getElementById('myChartLine').style.visibility='hidden';
	document.getElementById('myChartPie').style.visibility='hidden';
	document.getElementById('selectFilterChartPie').style.visibility='hidden';
    } else {
	console.log("listTest1 : "+listTest1.toString());
	console.log("listTest2 : "+listTest2.toString());
	updateChartLine(listTest1,listTest2);
	updateChartPie(json, valueSelectFilterPie);
	document.getElementById('myChartLine').style.visibility='visible';
	document.getElementById('myChartPie').style.visibility='visible';
	document.getElementById('selectFilterChartPie').style.visibility='visible';
    }   
}

function createChartLine(label,data) {
    console.log("creating my chart line");
	var ctx = document.getElementById("myChartLine").getContext('2d');
	chartLine = new Chart(ctx, {
		type : 'line',
		data : {
			labels : label,
			datasets : [ {
				label : "Warnings",
				data : data,
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

function updateChartLine(label,data){
    console.log("updating chart line ");
    console.log("label --> "+label.toString() +"\n" + "data -->" + data.toString());
    removeData2(chartLine,label);
    console.log("label --> "+label.toString() +"\n" + "data -->" + data.toString());
    addData(chartLine,label,data);
}

function updateChartPie(json, valueSelectFilterPie){
    console.log("updating chart pie");
	removeData(chartPie);
	
	var listPie = createListLabelsCounts(json.listWarnings, valueSelectFilterPie);
	addData(chartPie,listPie[0],listPie[1]);
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
		var col3 = line.insertCell(3);
		//col0.innerHTML += arrayWarning[i].id;
		col0.innerHTML += "<a href=\"/PPFE/purchase?id="+arrayWarning[i].id+"\">"+arrayWarning[i].id+"</a>";
		col1.innerHTML += arrayWarning[i].voucherType;
		col2.innerHTML += arrayWarning[i].difference;
		col3.innerHTML += arrayWarning[i].priority;
	}
}

function fillHeadTable() {
	var bodyHead = document.getElementById("headTableWarnings");
	var line = bodyHead.insertRow(-1);
	var col0 = line.insertCell(0);
	var col1 = line.insertCell(1);
	var col2 = line.insertCell(2);
	var col3 = line.insertCell(3);
	col0.innerHTML += "#";
	col1.innerHTML += "Voucher Type";
	col2.innerHTML += "Difference";
	col3.innerHTML += "Priority";
}

function removeHeadRow() {
	var bodyHead = document.getElementById("headTableWarnings");
	bodyHead.deleteRow(-1);
}

function createListLabelsCounts(arrayWarning, valueSelected) {
	var listLabels = [];	// representing labels
	var listCount = [];   // representing datas
	if(valueSelected == "Amount"){
        	// creation of the list of different vouchers
        	for (var i = 0; i < arrayWarning.length; i++) {
        		// TODO: see polyfill
        		if (!listLabels.includes(arrayWarning[i].voucherType)) {
        			listLabels.push(arrayWarning[i].voucherType);
        		}
        	}
	}
	else if(valueSelected == "Priority"){
        	// creation of the list of different priorities
        	for (var i = 0; i < arrayWarning.length; i++) {
        		// TODO: see polyfill
        		if (!listLabels.includes(arrayWarning[i].priority)) {
        			listLabels.push(arrayWarning[i].priority);
        		}
        	}
	}
	// initialization of the count list to 0
	for (var i = 0; i < listLabels.length; i++) {
		listCount[i] = 0;
	}
	
	if(valueSelected == "Amount"){
		// creation of the count list
		for (var i = 0; i < arrayWarning.length; i++) {
			for (var j = 0; j < listLabels.length; j++) {
				if (arrayWarning[i].voucherType == listLabels[j]) {
					listCount[j]++;
				}
			}
		}
	}
	else if(valueSelected == "Priority"){
		// creation of the count list
		for (var i = 0; i < arrayWarning.length; i++) {
			for (var j = 0; j < listLabels.length; j++) {
				if (arrayWarning[i].priority == listLabels[j]) {
					listCount[j]++;
				}
			}
		}
	}
	
	var listReturn = [listLabels, listCount];
	
	return listReturn;
}

function createChartPie(listVoucher, listCount) {
    console.log("creating my chart pie");
	var ctx = document.getElementById("myChartPie").getContext('2d');
	chartPie = new Chart(ctx, {
		type : 'pie',
		data : {
			labels : listVoucher,
			datasets : [ {
				label : "Repartition of Warnings",
				data : listCount,
				backgroundColor : palette('tol', nbMaxVoucher).map(
						function(hex) {
							return '#' + hex;
						})
			} ],
		},
		options : {
			title : {
				display : true,
				text : 'Repartition of Warnings'
			}
		}
	});
}

function removeData(chart) {
    console.log("removing my datas from my chart");
	while(chart.data.labels.length != 0){
		chart.data.labels.pop();
		chart.data.datasets.forEach((dataset) => {
	        dataset.data.pop();
	    });
	}
	chart.update();
}
function removeData2(chart,labelArray) {
    console.log("removing my datas from my chart");
    console.log("label before -> " + labelArray.toString());
    
	while(chart.data.labels.length != 0){
		chart.data.labels.pop();
		    console.log("label during  -> " + labelArray.toString());
		chart.data.datasets.forEach((dataset) => {
	        dataset.data.pop();
	    });
	}
     chart.update();
     console.log(" label after ---->"+labelArray.toString());
}

function addData(chart, label, data) {
    console.log("adding my datas from my chart");
    console.log("label: "+ label.toString() + "   data:"+data.toString());	
	for(var i=0;i<label.length;i++){
		chart.data.labels.push(label[i]);
		chart.data.datasets.forEach((dataset) => {
		        dataset.data.push(data[i]);
		    });
	}
	chart.update();
}

function testUpdate1(){
    removeData(chartLine);
    addData(chartLine,['aaa','bbb','ccc','ddd','eee'],[1,2,3,4,5]);   
}

function testUpdate2(){
    removeData(chartLine);
    addData(chartLine,['xxx','yyy','fff','bbb','nnn'],[5,4,3,2,1]);   
}
