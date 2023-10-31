'use strict';

var SnackBar = function () {

    var show = function (text, duration) {
        Snackbar.show({
            text: text,
            pos: 'bottom-center',
            showAction: true,
            actionText: 'Fechar',
            actionTextColor: '#22b9ff',
            duration: duration ? duration : 4000
        });
    };

    return {
        show: function (text, duration) {
            show(text, duration);
        }
    }
}()
