(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .filter('findLanguageFromKey', findLanguageFromKey)
        .filter('findLanguageRtlFromKey', findLanguageRtlFromKey);

    var languages = {
        'ar-ly': { name: 'العربية', rtl: true },
        'zh-cn': { name: '中文（简体）' },
        'nl': { name: 'Nederlands' },
        'en': { name: 'English' },
        'fr': { name: 'Français' },
        'hi': { name: 'हिंदी' },
        'it': { name: 'Italiano' },
        'ja': { name: '日本語' },
        'ko': { name: '한국어' },
        'pl': { name: 'Polski' },
        'pt-br': { name: 'Português (Brasil)' },
        'ru': { name: 'Русский' },
        'es': { name: 'Español' },
        'tr': { name: 'Türkçe' }
        // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
    };

    function findLanguageFromKey() {
        return findLanguageFromKeyFilter;

        function findLanguageFromKeyFilter(lang) {
            return languages[lang].name;
        }
    }

    function findLanguageRtlFromKey() {
        return findLanguageRtlFromKeyFilter;

        function findLanguageRtlFromKeyFilter(lang) {
            return languages[lang].rtl;
        }
    }

})();
