var Index = function() {

    var dashboardMainChart = null;

    return {

        //main function
        init: function() {
            
            Index.initCharts();
            Index.initMiniCharts();
            
        },

        initCharts: function() {
            if (Morris.EventEmitter) {
                // Use Morris.Area instead of Morris.Line
                dashboardMainChart = Morris.Area({
                    element: 'sales_statistics',
                    padding: 0,
                    behaveLikeLine: false,
                    gridEnabled: false,
                    gridLineColor: false,
                    axes: false,
                    fillOpacity: 1,
                    data: [{
                        period: '2011 Q1',
                        sales: 1400,
                        profit: 400
                    }, {
                        period: '2011 Q2',
                        sales: 1100,
                        profit: 600
                    }, {
                        period: '2011 Q3',
                        sales: 1600,
                        profit: 500
                    }, {
                        period: '2011 Q4',
                        sales: 1200,
                        profit: 400
                    }, {
                        period: '2012 Q1',
                        sales: 1550,
                        profit: 800
                    }],
                    lineColors: ['#399a8c', '#92e9dc'],
                    xkey: 'period',
                    ykeys: ['sales', 'profit'],
                    labels: ['Sales', 'Profit'],
                    pointSize: 0,
                    lineWidth: 0,
                    hideHover: 'auto',
                    resize: true
                });

            }
        },

        redrawCharts: function() {
            dashboardMainChart.resizeHandler();
        },

        initMiniCharts: function() {

            $("#sparkline_bar").sparkline([8, 9, 10, 11, 10, 10, 12, 10, 10, 11, 9, 12, 11], {
                type: 'bar',
                width: '100',
                barWidth: 6,
                height: '45',
                barColor: '#F36A5B',
                negBarColor: '#e02222'
            });

            $("#sparkline_bar2").sparkline([9, 11, 12, 13, 12, 13, 10, 14, 13, 11, 11, 12, 11], {
                type: 'bar',
                width: '100',
                barWidth: 6,
                height: '45',
                barColor: '#5C9BD1',
                negBarColor: '#e02222'
            });
        }

    };

}();