/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.998, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "Reptiles"], "isController": false}, {"data": [1.0, 500, 1500, "Main Catalog"], "isController": false}, {"data": [1.0, 500, 1500, "Birds"], "isController": false}, {"data": [1.0, 500, 1500, "Login"], "isController": false}, {"data": [1.0, 500, 1500, "Login-0"], "isController": false}, {"data": [1.0, 500, 1500, "Login-1"], "isController": false}, {"data": [1.0, 500, 1500, "Home-0"], "isController": false}, {"data": [0.996, 500, 1500, "Home-1"], "isController": false}, {"data": [1.0, 500, 1500, "Dogs"], "isController": false}, {"data": [1.0, 500, 1500, "Sign In"], "isController": false}, {"data": [0.992, 500, 1500, "Sign Out"], "isController": false}, {"data": [1.0, 500, 1500, "Sign Out-0"], "isController": false}, {"data": [0.996, 500, 1500, "Home"], "isController": false}, {"data": [0.992, 500, 1500, "Sign Out-1"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 1500, 0, 0.0, 7.037999999999999, 0, 975, 2.0, 7.0, 12.0, 56.0, 23.08082906337996, 69.38714808948437, 13.81241861603502], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Reptiles", 50, 0, 0.0, 3.68, 1, 55, 2.0, 5.899999999999999, 9.699999999999974, 55.0, 1.1448983330280271, 4.1502564572265985, 0.28175232414361606], "isController": false}, {"data": ["Main Catalog", 125, 0, 0.0, 3.1439999999999997, 0, 56, 2.0, 4.400000000000006, 6.699999999999989, 54.179999999999964, 2.26223871142883, 11.085853372997919, 0.9857528447651795], "isController": false}, {"data": ["Birds", 25, 0, 0.0, 3.0000000000000004, 1, 13, 2.0, 7.0, 11.199999999999996, 13.0, 2.6480245736680437, 9.593917156551212, 0.643904412932952], "isController": false}, {"data": ["Login", 125, 0, 0.0, 8.52, 2, 176, 4.0, 13.0, 24.499999999999943, 145.57999999999942, 2.2709105442918394, 11.669464144593416, 3.568256900161689], "isController": false}, {"data": ["Login-0", 125, 0, 0.0, 3.7120000000000015, 1, 57, 2.0, 5.400000000000006, 11.099999999999966, 54.91999999999996, 2.271075581395349, 0.42804451875908434, 2.0670336346293605], "isController": false}, {"data": ["Login-1", 125, 0, 0.0, 4.744, 0, 172, 2.0, 9.0, 12.0, 134.55999999999926, 2.271075581395349, 11.242267697356468, 1.5014825865279797], "isController": false}, {"data": ["Home-0", 125, 0, 0.0, 1.6479999999999995, 0, 24, 1.0, 3.0, 4.699999999999989, 22.179999999999964, 2.2627710799753813, 0.3226216578871149, 0.4004044137612686], "isController": false}, {"data": ["Home-1", 125, 0, 0.0, 6.712, 0, 619, 1.0, 4.400000000000006, 8.0, 461.1799999999969, 2.262853005068791, 2.9854632908671253, 0.40262872805032585], "isController": false}, {"data": ["Dogs", 50, 0, 0.0, 3.959999999999999, 1, 30, 2.0, 9.899999999999999, 17.04999999999996, 30.0, 1.1437983254792514, 4.798591412362172, 0.27701365695200625], "isController": false}, {"data": ["Sign In", 125, 0, 0.0, 3.128, 1, 35, 2.0, 5.400000000000006, 9.699999999999989, 32.659999999999954, 2.2652724669723274, 8.678241123529839, 1.3206715456860152], "isController": false}, {"data": ["Sign Out", 125, 0, 0.0, 20.48, 1, 975, 4.0, 13.0, 25.399999999999864, 908.1799999999987, 2.271983714420735, 11.689267461330838, 2.347189925342615], "isController": false}, {"data": ["Sign Out-0", 125, 0, 0.0, 2.504000000000001, 0, 48, 2.0, 4.0, 5.0, 41.23999999999987, 2.272025010451315, 0.7388518832815312, 1.1848255425595724], "isController": false}, {"data": ["Home", 125, 0, 0.0, 8.464, 0, 619, 2.0, 8.0, 12.0, 467.419999999997, 2.2627710799753813, 3.3079768620343217, 0.8030185649053257], "isController": false}, {"data": ["Sign Out-1", 125, 0, 0.0, 17.744000000000003, 0, 974, 2.0, 8.0, 13.699999999999989, 907.1799999999987, 2.272025010451315, 10.950628044513513, 1.16240704577676], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 1500, 0, "", "", "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
