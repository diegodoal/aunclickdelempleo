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

function graph_education(num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13) {

  var container = document.getElementById('graph-education'),
        d1 = [[0, num0]],
        d2 = [[0, num1]],
        d3 = [[0, num2]],
        d4 = [[0, num3]],
        d5 = [[0, num4]],
        d6 = [[0, num5]],
        d7 = [[0, num6]],
        d8 = [[0, num7]],
        d9 = [[0, num8]],
        d10 = [[0, num9]],
        d11 = [[0, num10]],
        d12 = [[0, num11]],
        d13 = [[0, num12]],
        d14 = [[0, num13]],
        graph;

  graph = Flotr.draw(container, [
            { data : d1, label : 'Sin estudios' },
            { data : d2, label : 'Grado' },
            { data : d3, label : 'PCPI / Garantía social' },
            { data : d4, label : 'Licenciado / Arquitecto / Ingeniero' },
            { data : d5, label : 'Certificado de escolaridad / Graduado en ESO' },
            { data : d6, label : 'Título propio / Carrera no homologada' },
            { data : d7, label : 'BUP / Bachillerato / COU' },
            { data : d8, label : 'Máster / Título propio de postgrado' },
            { data : d9, label : 'FP 1 / Técnico medio' },
            { data : d10, label : 'Doctorado' },
            { data : d11, label : 'FP 2 / Técnico superior' },
            { data : d12, label : 'Oposiciones' },
            { data : d13, label : 'Diplomado / Arquitecto técnico / Ingeniero técnico' },
            { data : d14, label : 'Otros Cursos' },
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
                position: 'se',
                margin: -50,
                backgroundColor : '#D2E8FF'
              },
              title : 'Nivel de estudios'
  });
}