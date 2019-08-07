var app = angular.module('produtosApp', ['ngRoute']);

app.config(function ($routeProvider) {

    $routeProvider.when('/cadastro', {
        controller: 'CadastroProdutosController',
        templateUrl: 'templates/cadastro.html'
    }).when('/cadastro/:id', {
        controller: 'CadastroProdutosController',
        templateUrl: 'templates/cadastro.html'
    }).when('/tabela', {
        controller: 'TabelaProdutosController',
        templateUrl: 'templates/tabela.html'
    }).otherwise('/tabela');

});

app.controller('TabelaProdutosController', function ($scope, ProdutosService) {
    
    listar();

    function listar() {
        ProdutosService.listar().then(function (resposta) {
            $scope.produtos = resposta.data;
        });
    }

    $scope.excluir = function (produto) {
        ProdutosService.excluir(produto).then(listar);
    };
});

app.controller('CadastroProdutosController', function ($routeParams, $scope, $location, ProdutosService) {
    
    var id = $routeParams.id;
    
    if(id) {
        ProdutosService.getProduto(id).then(function(resposta) {
           $scope.produto = resposta.data; 
        });
    } else {
        $scope.produto = {};
    }
    

    function salvar(produto) {
        $scope.produto = {};
        return ProdutosService.salvar(produto);
    };
    
    $scope.salvar = function (produto) {
        salvar(produto).then(redirecionarTabela);
    }; 

    $scope.salvarCadastrarNovo = salvar; 

    function redirecionarTabela() {
        $location.path('/tabela');
    };

    $scope.cancelar = function () {
        $scope.produto = {};
        redirecionarTabela();
    };
});

app.service('ProdutosService', function ($http) {

    var api = 'http://localhost:8080/CRUD-back/api/produtos';

    this.getProduto = function (id) {
        return $http.get(api + '/' + id);
    };

    this.listar = function () {
        return $http.get(api);
    };

    this.salvar = function (produto) {
        if (produto.id) {
            return $http.put(api + '/' + produto.id, produto);
        } else {
            return $http.post(api, produto);
        }
    };

    this.excluir = function (produto) {
        return $http.delete(api + '/' + produto.id);
    };

}

);

