function graph_drivingLicense(num_withLicense, num_withouLicense) {

  var container = document.getElementById('graph-drivingLicense'),
        d1 = [[0, num_withLicense]],
        d2 = [[0, num_withouLicense]],
        graph;

  graph = Flotr.draw(container, [
            { data : d1, label : 'Con carnet de conducir' },
            { data : d2, label : 'Sin carnet de conducir' }
          ],
          {
            HtmlText : false,
            grid : {
                verticalLines : false,
                horizontalLines : false
            },
              xaxis : { showLabels : false },
              yaxis : { showLabels : false },
              pie : {
                show : true,
                explode : 6
              },
              mouse : { track : true },
              legend : {
                position : 'se',
                backgroundColor : '#D2E8FF'
              },
              title : 'Carnet de conducir'
  });
}

function graph_certificate(num_withoutCertificate, num_less33Certificate, num_more33Certificate, num_more66Certificate){
  var container = document.getElementById('graph-certificate'),
        d1 = [[0, num_withoutCertificate]],
        d2 = [[0, num_less33Certificate]],
        d3 = [[0, num_more33Certificate]],
        d4 = [[0, num_more66Certificate]],
        graph;

  graph = Flotr.draw(container, [
            { data : d1, label : 'Sin certificado' },
            { data : d2, label : 'Menos del 33%' },
            { data : d3, label : 'Más del 33%' },
            { data : d4, label : 'Más del 66%' }
          ],
          {
            HtmlText : false,
            grid : {
                verticalLines : false,
                horizontalLines : false
            },
              xaxis : { showLabels : false },
              yaxis : { showLabels : false },
              pie : {
                show : true,
                explode : 6
              },
              mouse : { track : true },
              legend : {
                position : 'se',
                backgroundColor : '#D2E8FF'
              },
              title : 'Certificado de discapacidad'
  });
}