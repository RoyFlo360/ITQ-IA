const readline = require('readline');
const shell = require('child_process').exec;

let newRead = () => readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

var ListaAnimales = [
    "Gallina",
    "Abeja",
    "Avíspa",
    "Perro",
    "Gato",
    "Burro",
    "Vaca",
    "Araña",
    "Ballena",
    "Nutria",
    "Oso",
    "Serpiente",
    "Humano",
    "Ornitorrinco",
    "Hámster",
    "Chispas",
    "Awarawasu",
    'Ahuízotl',
    'Mapache',
]
var ListaCaracterísticas = [
    'es vertebrado', //0
    'es mamífero', //1
    'es insecto', //2
    'es arácnido', //3
    'es ave', //4
    'es reptil', //5
    'come carne', //6
    'come plantas', //7
    'nace de huevos', //8
    'nace del vientre', //9
    'se mueve en el agua', //10
    'se mueve en la tierra', //11
    'se mueve en el aire', //12
    'sobrevive bajo el agua', //13
    'sobrevive fuera del agua', //14
    'se come', //15
    'es mascota de hogar', //16
    'se usa para trabajar', //17

    'es obeso y adorable', //18
    'vive en zonas húmedas', //19
    'tiene mano(s) de humano', //20
]
var AnimalesCaracterísticas = [
    '0,4,7,8,11,14,15,16',
    '2,7,8,11,12,14,17',
    '2,7,8,11,12,14',
    '0,1,6,7,9,10,11,14,16,17',
    '0,1,6,9,11,14,16',
    '0,1,7,9,11,14,17',
    '0,1,7,9,11,14,15,17',
    '3,6,8,11,14',
    '0,1,6,7,9,10,13',
    '0,1,6,7,9,10,11,13,14',
    '0,1,6,7,9,10,11,14',
    '0,5,6,8,11,14,15,16',
    '0,1,6,7,9,10,11,14,17,20',
    '0,1,6,8,10,11,13,14',
    '0,1,7,9,11,14,16',
    '0,1,6,7,9,10,11,14,16,18',
    '0,1,6,7,9,10,11,14,19',
    '0,1,6,9,10,11,13,14,19,20',
    '0,1,6,7,9,10,11,14,15,16,20'
]
var puntos = ListaAnimales.map(animal => 0);

console.log('Piense en uno de los animales: ');
for(let i = 0; i < ListaAnimales.length; i++) console.log(`\t* ${ListaAnimales[i]}`);
let possibles = AnimalesCaracterísticas.map((str, i) => ({i, str}));
let c = 0;
let asked = '';

/* shell('ls', (e, o) => {
    console.log(o);
}) */
action();


function action(){
    //console.clear();
    //get question
    let repetitions = ListaCaracterísticas.map(u => 0);
    for(let i = 0; i < possibles.length; i++) possibles[i].str.split(',').map(n => repetitions[+n]++);
    let options = repetitions.map((r, i) => ({i, r: Math.abs(possibles.length/2 - r)})).sort((i1, i2) => i1.r - i2.r);
    //console.log('options\n', options);
    let qIndex = options[0].i, i;
    for(i = 1; asked.includes(qIndex); i++) qIndex = options[i].i;
    asked += `,${qIndex}`;
    //ask question
    console.log(`Q ${++c}: ¿El animal ${ListaCaracterísticas[qIndex]}?`);
    console.log('No: 0\tSí: 1');
    let r = newRead();
    r.question('R: ', (input) => {
        r.close();
        possibles = possibles.filter(({str}) => !!str.split(',').includes(`${qIndex}`) == !!+input);
        //console.log('possibles\n', possibles);
        if(possibles.length > 1 && c < 21) action();
        else end();
    });
}

function end(){
    if(possibles.length > 1) console.log(`El animal puede ser: ${possibles.map(({i}) => ListaAnimales[i])}`);
    else console.log(`El animal es: ${possibles.map(({i}) => ListaAnimales[i])}`);
}