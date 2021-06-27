let shell = require('child_process').exec;

let cellsMatrix, sizeI, sizeJ;
let figures = {};//: {id: cellId[]}
let colors = {};//: {id: length}
let currentColor;
let currentColorM;
let currentId;

shell('cat image.json', (e, output) => {
    //console.log(typeof output)
    //console.log(output[40733],output[40734],output[40735],output[40736],output[40737])
    cellsMatrix = JSON.parse(output);
    sizeI = cellsMatrix.length;
    sizeJ = cellsMatrix[0].length;
    for(let i = 0; i < sizeI; i++){
        for(let j = 0; j < sizeJ; j++){
            currentId = `${i}_${j}`;
            currentColorM = cellsMatrix[i][j];
            currentColor = `${currentColorM}`;
            if(currentColor == `255,255,255`) continue;
            if(`${Object.values(figures)}`.includes(currentId)) continue;
            console.log(currentColor, currentId);
            figures[currentId] = [];
            if(!colors[currentColor]) colors[currentColor] = 0;
            colors[currentColor]++;
            examineAdjacents(i, j);
            //generate figure
            //al crear la figura tiene que buscar recursivamente y añadir las celulas de esa figura
            //busca en todos lo adyacentes y añade a un historial cual ya buscó
        }
    }
    for(let color in colors) console.log(color + ": " + colors[color]);
});

function examineAdjacents(i, j){
    let colorM = cellsMatrix[i][j];
    let color = `${colorM}`, id = `${i}_${j}`;
    if(color != currentColor || `${Object.values(figures)}`.includes(id)) return;
    figures[currentId].push(id);
    for(let si of [i-1, i, i+1]) for(let sj of [j-1, j, j+1]) examineAdjacents(si, sj);
}

function colorsOverlapp(c1, c2){
    let r = [];
    for(let i = 0; i < c1.length; i++) r[i] = (c1[i] + 1)/(c2[i] + 1);
    return r;
}