var gulp = require('gulp');
var sass = require('gulp-sass');
var sourcemaps = require('gulp-sourcemaps');
var autoprefixer = require('gulp-autoprefixer');
var sassdoc = require('sassdoc');

var input = './src/main/resources/static/scss/**/*.scss';
var output = './src/main/resources/static/css';

var sassOptions = {
    errLogToConsole: true,
    outputStyle: 'expanded',
    dest: './src/main/resources/static/sassdoc'
};

var autoprefixerOptions = {
    browsers: ['last 2 versions', '> 5%', 'Firefox ESR']
};

gulp.task('sass', function () {
    return gulp
        .src(input)
        .pipe(sourcemaps.init())
        .pipe(sass(sassOptions).on('error', sass.logError))
        .pipe(sourcemaps.write())
        .pipe(autoprefixer(autoprefixerOptions))
        .pipe(gulp.dest(output));
});

gulp.task('sassdoc', function () {
    return gulp
        .src(input)
        .pipe(sassdoc())
        .resume();
});

gulp.task('watch', function() {
    return gulp
        .watch(input, ['sass'])
        .on('change', function(event) {
            console.log('File ' + event.path + ' was changed: ' + event.type);
        });
});

gulp.task('default', ['sass', 'watch']);