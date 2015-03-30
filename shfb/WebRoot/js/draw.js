var subTitle="";


//基础饼图
function pieBasic(title,data,div){
    $('#'+div).highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        colors: ['#2f7ed8', '#3A006F', '#009100', '#910000', '#1aadce', 
                 '#AE00AE', '#f28f43', '#46A3FF', '#c42525', '#a6c96a','#d0d0d0'],
        title: {
            text: title,
            style:{
            	fontWeight: 'bold',
            	fontSize:'18px'
            }
        },
		subtitle: {                                                        
            text: subTitle                              
        }, 
        tooltip: {
    	    pointFormat: '<b>{point.percentage:.2f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.2f} %'
                },
                showInLegend: true
            }

        },
        credits:{
             enabled:false // 禁用版权信息
        },
        series: [{
            type: 'pie',
            name: title,
            data: data
        }]
    });
	//颜色渐变配置
//	Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
//	    return {
//	        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
//	        stops: [
//	            [0, color],
//	            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
//	        ]
//	    };
//	});
};
//基础线图
function line(title,xAxis,yName,series,div) {
    $('#'+div).highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: title,
            style:{
            	fontWeight: 'bold',
            	fontSize:'18px'
            }
        },
        subtitle: {
            text: subTitle
        },
        xAxis: {
            categories: xAxis
        },
        yAxis: {
            title: {
                text: yName
            }
        },
        tooltip: {
            enabled: true,
            formatter: function() {
                return this.x +': '+ this.y;
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        credits:{
             enabled:false // 禁用版权信息
        },
        legend:{
        	enabled:false 
        },
        series: series
    });
};
//基础柱图
function column(title,xAxis,yName,series,div){
	$('#'+div).highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: title,
            style:{
            	fontWeight: 'bold',
            	fontSize:'18px'
            }
        },
        subtitle: {
            text: subTitle
        },
        xAxis: {
            categories: xAxis
        },
        yAxis: {
            min: 0,
            title: {
                text: yName
            }
        },
        tooltip: {
//            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
//            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
 //               '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
//            footerFormat: '</table>',
//            shared: true,
//            useHTML: true
            formatter: function() {
                return this.x +': '+ this.y;
            }
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        credits:{
             enabled:false // 禁用版权信息
        },
        legend:{
        	enabled:false 
        },
        series: series
    });
}
//条状图
function bar(title,xAxis,yName,series,div){
	 $('#'+div).highcharts({                                           
        chart: {                                                           
            type: 'bar'                                                    
        },                                                                 
        title: {                                                           
            text: title,
            style:{
            	fontWeight: 'bold',
            	fontSize:'18px'
            }
        },                                                                 
        subtitle: {                                                        
            text: subTitle                              
        },                                                                 
        xAxis: {                                                           
            categories: xAxis,
            title: {                                                       
                text: null                                                 
            }                                                              
        },                                                                 
        yAxis: {                                                           
            min: 0,                                                        
            title: {                                                       
                text: yName,                             
                align: 'high'                                              
            },                                                             
            labels: {                                                      
                overflow: 'justify'                                        
            }                                                              
        },                                                                 
        tooltip: {                                                         
            valueSuffix: ' millions'                                       
        },                                                                 
        plotOptions: {                                                     
            bar: {                                                         
                dataLabels: {                                              
                    enabled: true                                          
                }                                                          
            }                                                              
        },                                                                 
        legend: {                                                          
            layout: 'vertical',                                            
            align: 'right',                                                
            verticalAlign: 'top',                                          
            x: -40,                                                        
            y: 100,                                                        
            floating: true,                                                
            borderWidth: 1,                                                
            backgroundColor: '#FFFFFF',                                    
            shadow: true                                                   
        },                                                                 
        credits: {                                                         
            enabled: false                                                 
        },                                                                 
        series: series                                                                
    }); 
}