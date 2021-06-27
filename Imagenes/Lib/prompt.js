module.exports = {
    prompt
}

const readline = require('readline');
const exec = require('child_process').exec;


let newRead = () => readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

async function prompt(msg){
    return new Promise(resolve => {
        let prompt = newRead();
        prompt.question(msg, text => {
            prompt.close();
            resolve(text);
        });
    })
}