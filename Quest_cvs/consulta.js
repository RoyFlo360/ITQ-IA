const readline = require('readline');
const shell = require('child_process').exec;

let newRead = () => readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

let database /* {[charac: string]: animals[]} */ = {};
let databaseNot = {};
let animals = [], characs = [];

shell('cat BD/animales_characs.csv', (e, o) => {
    let [characLine, ...animalLines] = o.split('\n').map(line => line.split(','));
    animals = animalLines.map(([name]) => name);
    //console.log([characLine, ...animalLines])
    for(let i = 1; i < characLine.length; i++){
        characs[i - 1] = characLine[i];
        database[characLine[i]] = [];
        databaseNot[characLine[i]] = [];
        for(let j = 0; j < animalLines.length; j++) (+animalLines[j][i] ? database : databaseNot)[characLine[i]].push(animalLines[j][0]);
    }
    //console.log(database);
    //console.log(databaseNot);
    action();
})


function action(){
    console.log("Escribe tu pregunta: ");
    let prompt = newRead();
    prompt.question('', (text) => {
        prompt.close();
        let ors = text.split(' o ');
        let answers = ors.reduce((acc, or) => {
            let ands = or.split(' y ').reduce((acc, and) => {
                let words = and.split(' ');
                let charac = null;
                while(words.length && !characs.includes(charac)) charac = words.shift();
                if(characs.includes(charac)) return arrayAnd(
                    acc,
                    (and.split(' ').includes('no') ? databaseNot : database)[charac]
                ); else return acc;
            }, animals);
            return [...new Set([...acc, ...ands])];
        }, []);
        console.log(answers);
        action();
    });
}

function arrayAnd(a1, a2){
    let ret = [];
    if(a1.length >= a2.length) for(let i = 0; i < a1.length; i++) if(a2.includes(a1[i])) ret.push(a1[i]);
    else for(let i = 0; i < a2.length; i++) if(a1.includes(a2[i])) ret.push(a2[i]);
    return ret;
}