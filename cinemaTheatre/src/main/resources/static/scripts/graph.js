var graph;
var xPadding = 30;
var yPadding = 10;
////////////////////////////////////////////
var data = { values:[
  { X: "MON", Y: 270 },
  { X: "TUE", Y: 350 },
  { X: "WED", Y: 250 },
  { X: "THU", Y: 70 },
  { X: "FRI", Y: 21 },
  { X: "SAT", Y: 275 },
  { X: "SUN", Y: 425 }
]};
////////////////////////////////////////////

function getMaxY() {
  var max = 0;

  for(var i = 0; i < data.values.length; i ++) {
  if(data.values[i].Y > max) {
            max = data.values[i].Y;
           }
	}

//max += 10 - max % 10;
return max;
}

function getMinY(){
	var min = 2147483647;
  
  for(var i = 0; i < data.values.length; i ++) {
  	if(data.values[i].Y < min) {
    	min = data.values[i].Y;
    }
  }
  return min;
}

function getXPixel(val) {
  return ((graph.width() - xPadding) / data.values.length) * val + (xPadding * 1.5);
}

function getYPixel(val) {
  return graph.height()/2 - (((graph.height()/2 - 10 - yPadding) / getMaxY()) * val) - yPadding;
}

graph = $('#graph');
var c = graph[0].getContext('2d');            

c.lineWidth = 2;
c.strokeStyle = '#000000';
c.font = 'italic 8pt sans-serif';
c.textAlign = "center";

c.beginPath();
c.moveTo(xPadding, 0);
c.lineTo(xPadding, graph.height()/2 - yPadding);
c.lineTo(graph.width(), graph.height()/2 - yPadding);
c.stroke();


for(var i = 0; i < data.values.length; i ++) {
  c.fillText(data.values[i].X, getXPixel(i), graph.height()/2 - yPadding + 20);
}

c.textAlign = "right";
c.textBaseline = "middle";

for(var i = 0; i <= getMaxY(); i += getMaxY()/10) {
  c.fillText(Math.round(i), xPadding - 10, getYPixel(i));
}

c.strokeStyle = '#ff0000';

c.beginPath();
c.moveTo(getXPixel(0), getYPixel(data.values[0].Y));
for(var i = 1; i < data.values.length; i ++) {
  c.lineTo(getXPixel(i), getYPixel(data.values[i].Y));
}
c.stroke();

c.fillStyle = '#000000';

for(var i = 0; i < data.values.length; i ++) {  
  c.beginPath();
  c.arc(getXPixel(i), getYPixel(data.values[i].Y), 4, 0, Math.PI * 2, true);
  c.fill();
}