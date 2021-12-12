
import walkdir from "walkdir/walkdir.js"
import * as fs from 'fs';

function count_lines(all_files, i, callback) {
    var count = 0;
    fs.createReadStream(all_files[i]).on('data', function(chunk) {
        count += chunk.toString('utf8')
            .split(/\r\n|[\n\r\u0085\u2028\u2029]/g)
            .length-1;
    }).on('end', function() {
        // console.log(f, count);
        if (i !== all_files.length-1) {
            count_lines(all_files, i + 1, callback)
        }
        callback(count, i);
    }).on('error', function(err) {
        console.error(err);
    });

}

function getFiles(path) {
    let files = [];
    walkdir.sync(path, function(path) {
        files.push(path);
    });
    return files;
}

function main() {
    var t0 = performance.now();
    var lines_total = 0;
    let all_files = getFiles("pam08").filter(file => !fs.lstatSync(file).isDirectory());

    // file.walkSync(root, function syncCallback(dirPath, dirs, files) {
    //     files.forEach(function(f) {
    //         all_files.push(file.path.join(dirPath, f))
    //     })
    // });

    count_lines(all_files, 0, function(counter, i) {
        lines_total += counter;
        if (i === all_files.length-1) {
            var t1 = performance.now();
            console.log(lines_total);
            console.log("Synchronous: " + (t1 - t0) + " milliseconds.");
        }
    });
}


main();
