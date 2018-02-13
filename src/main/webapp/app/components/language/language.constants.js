(function () {
    'use strict';

    angular
        .module('hospitalApp')

        /*
         Languages codes are ISO_639-1 codes, see http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
         They are written in English to avoid character encoding issues (not a perfect solution)
         */
        .constant('LANGUAGES', [
            'fr',
            'ar-ly',
            'zh-cn',
            'nl',
            'en',
            'hi',
            'it',
            'ja',
            'ko',
            'pl',
            'pt-br',
            'ru',
            'es',
            'tr'
            // jhipster-needle-i18n-language-constant - JHipster will add/remove languages in this array
        ]
    );
})();
