app.service('ItemCategoryService', function($resource) {

    return $resource('/items/byCategory/:category', { id: '@category' }, {
        query: {method: 'GET', isArray: true}
    });
});

app.service('ItemService', function($resource) {

    return $resource('/items/:id', { id: '@id' }, {
        query: {method: 'GET', isArray: true},
        setEnabled: {method: 'PUT', url: '/items/setEnabled' },
        save: {method: 'POST', url: '/items'},
    });
});


app.service('OrderService', function($resource) {

    return $resource('/orders/:id', { id: '@_id' }, {
        query: {method: 'GET', isArray: true},
        moveNextState: {method: 'POST', url: '/orders/moveNextState' }
    });
});

app.factory('Alert', function() {

    return {

        showAlert : function(title, msg){

            $('#myModalLabel').text(title)
            $('.modal-body').text(msg)

            $('#myModal').modal('show');
        }
    };
});

app.service('Session', function () {

    this.create = function (data) {

        this.id = data.id;
        this.username = data.username;
        this.name = data.name;
        this.email = data.email;
        this.role = data.role;
    };

    this.invalidate = function () {
        this.id = null;
        this.username = null;
        this.name = null;
        this.email = null;
        this.role = null;
    };
    return this;
});

app.service('AuthSharedService', function ($rootScope, $http, $resource, $location, authService, Session) {
    return {
        login: function (userName, password) {

            var config = {
                params: {
                    username: userName,
                    password: password
                },
                ignoreAuthModule: 'ignoreAuthModule'
            };

            $http.post('authenticate', '', config)
                .success(function (data, status, headers, config) {
                    authService.loginConfirmed(data);
                }).error(function (data, status, headers, config) {
                    $rootScope.authenticationError = true;
                    Session.invalidate();
                });
        },
        getAccount: function () {
            $rootScope.loadingAccount = true;
            $http.get('/user/logged').then(function (response) {
                if(response.data){
                    authService.loginConfirmed(response.data);
                }
            });
        },
        isAuthorized: function (authorizedRole) {

            if (authorizedRole == '*') {
                return true;
            }

            return !!Session.username && Session.role == authorizedRole;
        },
        logout: function () {
            $rootScope.authenticationError = false;
            $rootScope.authenticated = false;
            $rootScope.account = null;
            $http.get('logout');
            Session.invalidate();
            authService.loginCancelled();

            $location.path('/home');
        }
    };
});