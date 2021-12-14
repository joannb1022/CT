import walkdir from "walkdir/walkdir.js"
import * as fs from 'fs';
import  waterfall  from 'async/waterfall.js';
import apply from 'async/apply.js'

var total = 0;

function getFiles(path) {
    let files = [];
    walkdir.sync(path, function(path) {
        files.push(path);
    });
    return files;
}

function getFileLinesNumber(file) {
    return new Promise((resolve, reject) => {
        let count = 0;
        fs.createReadStream(file).on('data', function(chunk) {
            count += chunk.toString('utf8')
            .split(/\r\n|[\n\r\u0085\u2028\u2029]/g)
            .length-1;
        }).on('end', function() {
            resolve(count);
        }).on('error', function(err) {
            console.error(err);
        });
    });
}

function waterfallFunction(file, callback) {
        getFileLinesNumber(file).then(result => {
        callback();
        console.log("File " + file + " number of lines: " + result);
        total = total + result;
    });
}

function main() {
    let files = getFiles("pam08").filter(file => !fs.lstatSync(file).isDirectory());
    let tasks = [];

    files.forEach(file => tasks.push(function(callback){waterfallFunction(file, callback)}));
    let start = new Date();

    waterfall(tasks, function(err, result) {
        console.log("Toral number of lines: " + total);
        let end = new Date();
        let elapsed = end.getTime() - start.getTime();
        console.log(elapsed);
    });
}

main();
