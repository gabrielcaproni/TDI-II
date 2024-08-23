const soma = function(op1, op2){
    return op1 + op2
}

const sub = function (op1, op2){
    return op1 - op2;
}

function hof(operation, writer, op1, op2){
    writer(operation(op1, op2));
}

hof(sub, console.log, 1, 1);

console.log(soma(5, 5));
