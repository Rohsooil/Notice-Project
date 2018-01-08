var express = require('express');
var router = express.Router();
var webdriver = require('selenium-webdriver'),
    By = webdriver.By;
var chrome = require('chromedriver');
var request = require("request");
var cheerio = require("cheerio");
/* GET home page. */
router.get('/', function (req, res, next) {


    var JsScript = '';
    //워커와 레포트 준비
    const report = new webdriver.Builder().forBrowser('chrome').build();

    //레포트의 준비
    const reportUrl = 'http://wind.gachon.ac.kr/client/program/programList.do?programType=newProgram';
    report.get(reportUrl);

    function function1() {
        report.findElement(By.css('#cBody > div > div > form > div > div.tableWrap.rowPro > table > tbody')).getAttribute("innerHTML").then(function (profile) {
            console.log(profile);
            res.render('index', {
                title: profile
            });
        });
    }
    setTimeout(function1, 10000);


});

module.exports = router;