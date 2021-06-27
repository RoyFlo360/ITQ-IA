#!/usr/bin/node
let shell = require('child_process').exec;
let fs = require('fs');
//let Atils = require('/usr/lib/asyncUtils');

let [u, u2, ...text] = process.argv;


let SCALE = 10;
let TEXT = ` ${text.join(' ') || process.exit(0)} `.toUpperCase();
let imageMatrix = [...Array(5*SCALE)].map(u => []);

function main(){
    for(let i = 0, p = 0; i < TEXT.length; i++){
        let cells = letterToMatrix(TEXT[i]);
        if(!cells) continue;
        let w = cells[0].length + 1;
        for(let y = 0; y < 5*SCALE; y++)
            for(let x = 0; x < w*SCALE; x++)
                imageMatrix[y][p + x] = +cells[Math.floor(y/SCALE)][Math.floor(x/SCALE)] ? [0,135,255] : [255,255,255];
        p += w*SCALE;
    }

    let getInfoCommands = ';' || `
        echo    $(zenity --file-selection --file-filter="*.jpg");
        echo -n $(zenity --password --title Key);
    `

    fs.writeFile('Exec/image.json', JSON.stringify(imageMatrix), e => {
        if(e) return console.log('Error in JSON write.\n', e);
        shell(`python Transform/rgbJsonToPng.py${getInfoCommands}`, (e, o) => {
            if(e) return console.log('ERROR IN PYTHON.\n', e);
            let [image, password] = o.split('\n');
            //return console.log(o);
            //shell('steganography embed Assets/test1.png Exec/image.png Exec/embed.png holaquease');
            shell('xdg-open Exec/image.png');
            return;
            //shell(`steghide embed -ef Exec/image.png -cf ${image} -sf Exec/stego.jpg --passphrase ${password}`);
        })
    });


}



function letterToMatrix(char){
    return (letters[char] || letters.any).split('.').map(row => [...row]);
}


let letters = {
    'A': '0110.1001.1111.1001.1001.',
    'B': '1110.1001.1110.1001.1110.',
    'C': '0111.1000.1000.1000.0111.',
    'D': '1110.1001.1001.1001.1110.',
    'E': '1111.1000.1110.1000.1111.',
    'F': '1111.1000.1110.1000.1000.',
    'G': '0111.1000.1011.1001.0111.',
    'H': '1001.1001.1111.1001.1001.',
    'I': '111.010.010.010.111.',
    'J': '0001.0001.0001.1001.0110.',
    'K': '1001.1010.1100.1010.1001.',
    'L': '100.100.100.100.111.',
    'M': '10001.11011.10101.10001.10001.',
    'N': '1001.1101.1011.1001.1001.',
    'Ñ': '0110.1001.1101.1011.1001.',
    'O': '0110.1001.1001.1001.0110.',
    'P': '1110.1001.1110.1000.1000.',
    'Q': '0110.1001.1001.1011.0111.',
    'R': '1110.1001.1110.1010.1001.',
    'S': '0111.1000.0110.0001.1110.',
    'T': '111.010.010.010.010.',
    'U': '1001.1001.1001.1001.1111.',
    'V': '101.101.101.101.010.',
    'W': '10001.10001.10001.10101.01010.',
    'X': '1010.1010.0100.1010.1010.',
    'Y': '101.101.111.010.010.',
    'Z': '1111.0001.0010.0100.1111.',
    '!': '1.1.1.0.1.',
    '?': '110.001.110.000.100.',
    ',': '00.00.01.01.10.',
    ' ': '0.0.0.0.0',

    any: '11111.10001.10101.10001.11111'
}


main();