(function ($, window, document, undefined) {

var pluginName = "metisMenu",
    defaults = {
        toggle: true
    };

function Plugin(element, options) {
    this.element = element;
    this.settings = $.extend({}, defaults, options);
    this._defaults = defaults;
    this._name = pluginName;
    this.init();
}

Plugin.prototype = {
    init: function () {
        var $this = $(this.element),
            $toggle = this.settings.toggle;
        $('a',$this).click(function(){
        	 $('.click-bg').removeClass('click-bg');
             $(this).addClass('click-bg');
        });
        $this.find('li.active').has('ul').children('ul').addClass('metis-open').css('display', 'block');
        $this.find('li').not('.active').has('ul').children('ul').addClass('metis-close').css('display', 'none');

        $this.find('li').has('ul').children('a').on('click', function (e) {
            e.preventDefault();
            $(this).parent('li').toggleClass('active').children('ul').slideToggle().toggleClass('metis-close').toggleClass('metis-open');

            if ($toggle) {
                $(this).parent('li').siblings().removeClass('active').children('ul.metis-open').slideUp().toggleClass('metis-open');
            }
        });
    }
};

$.fn[ pluginName ] = function (options) {
    return this.each(function () {
        if (!$.data(this, "plugin_" + pluginName)) {
            $.data(this, "plugin_" + pluginName, new Plugin(this, options));
        }
    });
};

})(jQuery, window, document);