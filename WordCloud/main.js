let shell = require('child_process').exec;
let takenAccount = 'ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz';
let inputName = 'read.txt';
let outputName = 'output.txt';

let wordsScore = {};

//read file node
shell(`rm ${outputName}; cat ${inputName}`, (error, shellOutput) => {
    //console.log(shellOutput);
    count(shellOutput);
    let unsortedResults = [];
    for(let key in wordsScore) unsortedResults.push({x: key, value: wordsScore[key]});
    sortedResults = unsortedResults.sort((s1, s2) => {
        return -s1.value + str2.value;
    });
    for(let val of sortedResults) console.log(val);
    //drawCloud(sortedResults);
});

//count and save in wordsScore
function count(fileContent){
    let buffer = '';
    for(let i = 0; i < fileContent.length; i++){
        if(takenAccount.includes(fileContent[i])){
            buffer += fileContent[i];
        }else{
            if(buffer) wordsScore[buffer] = (wordsScore[buffer] || 0) + 1;
            buffer = '';
        }
    }
}

function drawCloud(data){

    // create a tag (word) cloud chart
    var chart = anychart.tagCloud(data);

    // set a chart title
    chart.title('15 most spoken languages')
    // set an array of angles at which the words will be laid out
    chart.angles([0])
    // enable a color range
    chart.colorRange(true);
    // set the color range length
    chart.colorRange().length('80%');

    // display the word cloud chart
    chart.container("container");
    chart.draw();
    }