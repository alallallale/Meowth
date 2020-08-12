/**
 * 
 */
// var ctx = document.getElementById('myChart').getContext('2d');
// var myChart = new Chart(ctx, {
// type : 'bar',
// data : {
// labels : [ 'Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange' ],
// datasets : [ {
// label : '날짜',
// data : [ 12, 19, 3, 5, 2, 3 ],
// backgroundColor : 'rgba(54, 162, 235, 0.2)',
// borderColor : 'rgba(54, 162, 235, 1)',
// borderWidth : 1
// } ]
// },
// options : {
// responsive : true,
// scales : {
// yAxes : [ {
// ticks : {
// beginAtZero : true
// }
// } ]
// }
// }
// });
$(document).ready(function() {
	var today = new Date();
	var thisYear = today.getFullYear();
	// console.log(thisYear);
	boardChart(thisYear, "board");
});
function boardChart(thisYear, chartType) {
	// console.log(thisYear + "," + chartType);
	$.ajax({
		url : "boardChart",
		type : "get",
		data : {
			chartYear : thisYear,
			chartType : chartType
		},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			// data.forEach(function(element) {
			// console.log(element);
			// });
			var labelArray = new Array();
			var dataArray = new Array();
			for (var i = 0; i < data.length; i++) {
				labelArray.push(data[i].month);
				dataArray.push(data[i].count);
			}

			arrayChart(labelArray, dataArray, chartType);
		},
		error : function() {
			alert('chart err');
		}
	});
}

var myChart
function arrayChart(labelArray, dataArray, chartType) {
	console.log("arratChart : " + chartType);
	var ctx = document.getElementById('myChart').getContext('2d');
	var selectYear = parseInt(labelArray[0].substr(0, 4));
	if (myChart == null) {
		myChart = new Chart(ctx, {
			type : 'bar',
			data : {
				labels : labelArray,
				datasets : [ {
					label : '월',
					data : dataArray,
					backgroundColor : 'rgba(85, 120, 235, 0.2)',
					borderColor : 'rgba(85, 120, 235, 1)',
					borderWidth : 1
				} ]
			},
			options : {
				legend : false,
				responsive : true,
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true,
						}
					} ]
				},
				title : {
					display : true,
					text : selectYear + '년도 ' + chartType,
					fontSize : 18
				},
			}
		});
	} else {
		myChart.data.labels = labelArray;
		myChart.data.datasets = [ {
			label : '월',
			data : dataArray,
			backgroundColor : 'rgba(85, 120, 235, 0.2)',
			borderColor : 'rgba(85, 120, 235, 1)',
			borderWidth : 1
		} ];
		myChart.options.title = {
			display : true,
			text : selectYear + '년도 ' + chartType,
			fontSize : 18
		}
		myChart.update();
		$('#chartbtn').text(chartType);
		$('button.lastYear').text(selectYear - 1 + '년도');
		$('button.lastYear').attr('onclick',
				'boardChart(' + (selectYear - 1) + ', "' + chartType + '")')
		$('button.nextYear').text(selectYear + 1 + '년도');
		$('button.nextYear').attr('onclick',
				'boardChart(' + (selectYear + 1) + ', "' + chartType + '")')
	}

}