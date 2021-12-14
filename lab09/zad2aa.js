
// import walkdir from "walkdir/walkdir.js"
// import * as fs from 'fs';

var walkdir = require('walkdir');
var fs = require('fs');

function count_lines(files, i, callback) {
    var count = 0;
    fs.createReadStream(files[i]).on('data', function(chunk) {
        count += chunk.toString('utf8')
            .split(/\r\n|[\n\r\u0085\u2028\u2029]/g)
            .length-1;
    }).on('end', function() {
        console.log(files[i], count);
        if (i !==files.length-1) {
            count_lines(files, i + 1, callback);
        }
        callback(count, i);
    }).on('error', function(err) {
        console.error(err);
    });

}

function main() {
    var total = 0;
    let files = [];

    walkdir.sync("./PAM08", function(path) {
      if(!fs.lstatSync(path).isDirectory())
        files.push(path);
    });

    let start = new Date();
    count_lines(files, 0, function(count, i) {
        total += count;
        if (i === files.length-1) {
            console.log(total);
            let end = new Date();
            console.log("TIME = " + (end.getTime() - start.getTime()));
        }
    });
}
main();
