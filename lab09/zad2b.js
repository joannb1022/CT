// import walkdir from "walkdir/walkdir.js"
// import * as fs from 'fs';

var walkdir = require('walkdir');
var fs = require('fs');



function getFileLinesNumber(file) {
    return new Promise((resolve, reject) => {
        let count = 0;
        fs.createReadStream(file).on('data', function(chunk) {
            count += chunk.toString('utf8')
            .split(/\r\n|[\n\r\u0085\u2028\u2029]/g)
            .length-1;
        }).on('end', function() {
            console.log( file,  count)
            resolve(count);
        }).on('error', function(err) {
            console.error(err);
        });
    });
}

function main() {
    let files = [];
    let tasks = [];
    walkdir.sync("./PAM08", function(path) {
      if(!fs.lstatSync(path).isDirectory())
        files.push(path);
    });

    let start = new Date();

    files.forEach(file => tasks.push(getFileLinesNumber(file)));

    Promise.all(tasks).then((values) => {
      let total = 0
      for (let i = 0; i<files.length; i++){
        total+=values[i]
      }
      console.log(total);
      let end = new Date();
      console.log("TIME = " + (end.getTime() - start.getTime()));
    })
}

main();
