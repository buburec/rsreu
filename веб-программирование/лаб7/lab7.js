function f1(x) {
    return (Math.pow(x, 3) + 2 * x) / (4 * Math.sin(x)) + Math.pow(Math.tan(4 * x), 3);
}

function f2(x) {
    return Math.pow(Math.sin(x), 2) - Math.abs(Math.log(x) + Math.exp(x)) / 4;
}

function f3(x) {
    return Math.sqrt(((Math.pow(Math.E), 3) + x) / 6) + Math.sin(x - 7) / 4;
}

function average(args) {
    return args.reduce((acc, val) => acc + val, 0) / args.length;
}

function zeroValues(args) {
    return args.reduce((acc, val) => val == 0 ? ++acc : acc, 0);
}

function lessThenZeroValues(args) {
    return args.reduce((acc, val) => val < 0 ? ++acc : acc, 0);
}

function memoize(f) {
    var cache = {};
    var calculated = 0;
    function calc(x) {
        if (cache[x]) {
            console.log("cached");
            return cache[x];
        }
        cache[x] = f(x);
        calculated++; 
        return cache[x];
    }
    calc.calculated = () => {
        return calculated;
    }
    calc.cached = (x) => {
        return cache[x];
    }
    return calc;
}

function log(f) {
    var timeCall;
    var arg;
    var result;
    function calc(x) {
        var start = (new Date()).getTime();
        var y = f(x);
        var end = (new Date()).getTime();
        timeCall = end - start;
        arg = x;
        result = y;
        return y;
    }
    calc.info = () => {
        return { x : arg, y : result, timeCall : timeCall};
    }
    return calc;
}

function apply(f) {
    var countCall = 0;
    function calc(x) {
        countCall++;
        return f(x);
    }
    calc.applied = () => {
        return countCall;
    }
    return calc;
}

function calculate(f, properties, x0, xn, h) {
    var y = Array.from({ length: Math.floor((xn - x0) / h) }, (_, i) => f(x0 + i * h));
    var props = Array.from({ length: properties.length }, (_, i) => properties[i](y));
    return {y_vals : y, properties : props};
}

// var func = call(log(memoize(f1)));

var func = memoize(log(f1));

// После вызова func с аргументом, вы получите результат выполнения log(f)
// Далее, вызов info() вернет объект с контекстом свойств из log
console.log(func(5));
console.log(func.call(func, func.info()));



