app.controller("HomeController", function($rootScope, Session){

    $rootScope.isUserAdmin = function(){
        return Session.role == 'ADMIN';
    }
});

app.controller("OrderController", function($scope, $location, ItemCategoryService, ItemService, OrderService, Alert){

    $scope.order = {size:{price:0}, base: {price:0}, sauce: {price:0}};
    $scope.order.toppings = [];

    $scope.sizes = ItemCategoryService.query({category:'SIZE'});
    $scope.bases = ItemCategoryService.query({category:'BASE'});
    $scope.sauces = ItemCategoryService.query({category:'SAUCE'});
    $scope.toppings = ItemCategoryService.query({category:'TOPPING'});

    $scope.setSize = function(item) {
        $scope.order.size = item;
    }

    $scope.setBase = function(item) {
        $scope.order.base = item;
    }

    $scope.setSauce = function(item) {
        $scope.order.sauce = item;
    }

    $scope.selectTopping = function(item) {

        var idx = $scope.order.toppings.indexOf(item);

        if (idx > -1) {
            $scope.order.toppings.splice(idx, 1);
        } else {
            $scope.order.toppings.push(item);
        }
    };

    $scope.getTotal = function(){

        var total = 0;
        total += $scope.order.size.price;
        total += $scope.order.base.price;
        total += $scope.order.sauce.price;

        angular.forEach($scope.order.toppings, function(value, key){
            total += value.price;
        });

        return total;
    };

    $scope.submitOrder = function(){

        OrderService.save(createOrder(), function(){
            Alert.showAlert('Message', 'Order saved');
            $location.path('/home');
        });
    };

    var createOrder = function(){

        var order = {items:[]};
        order.items.push({item:$scope.order.size});
        order.items.push({item:$scope.order.base});
        order.items.push({item:$scope.order.sauce});

        if($scope.order.toppings){
            var toppings = $scope.order.toppings;
            for(var i=0;i<toppings.length;i++){
                order.items.push({item:toppings[i]});
            }
        }

        return order;
    }
});

app.controller("OrdersController", function($scope, $http, OrderService, Alert){

    $scope.orders = OrderService.query();

    $scope.cancelOrder = function(order){

        OrderService.remove({id:order.id}, function(){
            Alert.showAlert('Message', 'Order cancelled');
            $scope.orders = OrderService.query();
        });
    }

    $scope.setOrderNextState = function(order){

        OrderService.moveNextState(order, function(){
            Alert.showAlert('Message', 'Order moved to the next state');
            $scope.orders = OrderService.query();
        });
    }
});

app.controller("ItemsController", function($scope, ItemService, Alert){

    $scope.items = ItemService.query();

    $scope.formError = false;

    $scope.newItem = {};

    $scope.updateItem = function(item){
        ItemService.save(item, function(){
            Alert.showAlert('Saved', 'Item saved');
            item.changed = false;
        });
    }

    $scope.newItemForm = function(){
        $('#myItemModal').modal('show');
    }

    $scope.saveItem = function(){

        $scope.formError = !$scope.itemForm.$valid;

        ItemService.save($scope.newItem, function(){

            $('#myItemModal').modal('hide');

            $scope.newItem = {};
            $scope.items = ItemService.query();
        });
    }
});

app.controller("LoginController", function($rootScope, $scope, $location, AuthSharedService){

    $scope.login = function () {
        $rootScope.authenticationError = false;
        AuthSharedService.login(
            $scope.username,
            $scope.password
        );
    };

    $scope.close = function(){

        if(!$rootScope.previousPath || $rootScope.previousPath == '/login' || $rootScope.previousPath == '/logout'){
            $rootScope.previousPath = '/home';
        }

        $location.path($rootScope.previousPath);
        $rootScope.authenticationError = false;
    };
});

app.controller('LogoutController', function (AuthSharedService) {
    AuthSharedService.logout();
});

app.controller('ErrorController', function ($scope, $routeParams) {

    $scope.code = $routeParams.code;

    switch ($scope.code) {
        case "403" :
            $scope.message = "Oops! You have come to unauthorised page.";
            break;
        case "404" :
            $scope.message = "Page not found.";
            break;
        default:
            $scope.code = 500;
            $scope.message = "Oops! unexpected error";
    }
});