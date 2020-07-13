var App = function() {
    
    var MediaSize = {
        xl: 1200,
        lg: 992,
        md: 991,
        sm: 576
    };
    var ToggleClasses = {
        headerhamburger: '.toggle-sidebar',
        inputFocused: 'input-focused',
    };
    var TOKEN_KEY = "jwtToken";
   var $logout = $("#logout");
   var $userName = $(".profilename");
   var $userPermissions = $(".profilepermissions");
   var $channelBody =$("#channelBody");
   var $userInfo = $("#content").hide();
   function getJwtToken() {
      return localStorage.getItem(TOKEN_KEY);
   }
   $("#save").click(function (){
      var $formsave = $("#work-experience");
      var formData = {
         name: $formsave.find('input[name="name"]').val(),
         type: $formsave.find('select[name="type"]').val(),
         description:  $formsave.find('input[name="des"]').val(),
         notes:  $formsave.find('input[name="idchannelhiden"]').val()
      };
      var dt =JSON.stringify(formData);
       $.ajax({
         url: "/api/task/insert",
         type: "POST",
         data: dt,
         contentType: "application/json; charset=utf-8",
         headers: createAuthorizationTokenHeader(),
         dataType: "json",
         success: function (data, textStatus, jqXHR) {
            location.reload();
         },
         error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status === 401) {
              console.log(dt);
            } else {
               console.log(dt);
            }
         }
      });
       
   });

   function setJwtToken(token) {
      localStorage.setItem(TOKEN_KEY, token);
   }

   function removeJwtToken() {
      localStorage.removeItem(TOKEN_KEY);
   }
   function createAuthorizationTokenHeader() {
      var token = getJwtToken();
      //console.log('authen...');
      if (token) {
         return {"Authorization": "Bearer " + token};
      } else {
         return {};
      }
   }
   function getTaskInformation(){
        var url_string = window.location.href ;
        var url = new URL(url_string);
        var _id = url.searchParams.get("id");
        console.log(_id);
        $tasktable = $("#tasktable");
        var idobj = JSON.parse('{"channelId":"'+_id+ '"}');
        console.log(idobj);
        if(_id) {
            $.ajax({
            url: "/api/channel/detail",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data:idobj,
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                var $taskprofile = $("#taskprofile");
                $("#idchannelhiden").val(data.channelId);
                $taskprofile.append($("<li>").html('<li class="list-group-item"><strong>Name: </strong> '+data.name+'</li>'));
                $taskprofile.append($("<li>").html('<li class="list-group-item"><strong>Source: </strong><a target=_blank href="http://'+data.source+'">'+data.source+'</a></li>'));
                $taskprofile.append($("<li>").html('<li class="list-group-item"><strong>Category: </strong>'+data.category+'</li>'));
                if(data.createdBy!==null)
                $taskprofile.append($("<li>").html('<li class="list-group-item"><strong>Created by: </strong>'+data.createdBy.firstname + ' '+data.createdBy.lastname +'</li>'));
                if(data.modifiedBy!==null)
                $taskprofile.append($("<li>").html('<li class="list-group-item"><strong>Modified by: </strong>'+data.modifiedBy.firstname + ' '+ data.modifiedBy.lastname+'</li>'));
                if(data.owner!==null) 
                $taskprofile.append($("<li>").html('<li class="list-group-item"><strong>Owner: </strong>'+data.owner.firstname+' ' + data.owner.lastname+'</li>'));
                
                $tasktable.append($taskprofile);
                $("#tstatus").text(data.status);
                $("#tduedate").text(data.duedate);
                $("#tcustomer").text(data.customer);
                $("#tclosedate").text(data.closedate);
                $("#treason").text(data.reason);
                $("#tdesctiption").text(data.desctiption);
                
                var $as = $("<ul>");
                data.assignTo.forEach( function (asign) 
                {
                    $as.append($("<li>").text(asign.firstname+' '+asign.lastname));
                });
                $("#tassignto").append($as);
                
                
                data.task.forEach( function (taskItem) 
                {
                    var $taskList = $("<tr>");
                    $taskList.append($('<td class="checkbox-column">').html('<label class="new-control new-checkbox checkbox-primary" style="height: 18px; margin: 0 auto;"><input type="checkbox" class="new-control-input todochkbox" id="todo-1"><span class="new-control-indicator"></span></label>'));
                    $taskList.append($("<td>").text(taskItem.taskId));
                    $taskList.append($("<td>").text(taskItem.name));
                    $taskList.append($("<td>").text(taskItem.type));
                    $taskList.append($("<td>").text(taskItem.status));
                    $taskList.append($("<td>").text(taskItem.dueDate));
                    var namedo;
                    taskItem.assignTo.forEach (function (assname){
                        if(namedo)
                            namedo = ', '+ assname.firstname + ' '+assname.lastname; 
                        else namedo = assname.firstname + ' '+assname.lastname; 
                    } );
                    $taskList.append($("<td>").text(namedo));
                    $taskList.append($('<td class="text-center" >').html('<ul class="table-controls"> <li><a href="javascript:void(0);" data-toggle="tooltip" data-placement="top" title="Edit"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit-2 text-success"><path d="M17 3a2.828 2.828 0 1 1 4 4L7.5 20.5 2 22l1.5-5.5L17 3z"></path></svg></a></li> <li><a href="javascript:void(0);" data-toggle="tooltip" data-placement="top" title="Delete"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-trash-2 text-danger"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg></a></li></ul>'));
                 $("#taskBody").append($taskList);
                });
                
                checkall('todoAll', 'todochkbox');
                $('[data-toggle="tooltip"]').tooltip();
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
               if (jqXHR.status === 401) {
                   console.log(jqXHR.responseText);
               } else {
                   console.log(jqXHR.responseText);

               }
            }
       });
        }
        else {
            $userInfo.hide();
        }
   }
   
   function getChannelInformation() {
        $.ajax({
            url: "/api/channel/profile",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
               
                var $channelList = $("<tr>");
                data.data.forEach( function (channelItem) 
                {
                    $channelList.append($("<td>").html('<a href=./task?id=' + channelItem.channelId +'>'+channelItem.channelId +  '</a>'));
                    $channelList.append($("<td>").text(channelItem.name));
                    $channelList.append($("<td>").text(channelItem.category));
                    $channelList.append($("<td>").text(channelItem.created));
                    $channelList.append($("<td>").text(channelItem.status));
                    $channelList.append($("<td>").html('<a href=\"javascript:void(0);\" title=\"Edit\" class=\"channelId\"> <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-edit-2 table-cancel\"> <path d=\"M17 3a2.828 2.828 0 1 1 4 4L7.5 20.5 2 22l1.5-5.5L17 3z\"></path> </svg></a>'));
                });
                 
                /*
                 * 
                var $authorities = $("<div>").text("Authorities:");
                $authorities.append($authorityList);
                */

                $channelBody.append($channelList);
                if (document.getElementById("zero-config")) { 
                    $('#zero-config').DataTable({
                    dom: '<"row"<"col-md-12"<"row"<"col-md-6"B><"col-md-6"f> > ><"col-md-12"rt> <"col-md-12"<"row"<"col-md-5"i><"col-md-7"p>>> >',
                    buttons: {
                        buttons: [
                            { extend: 'excel', className: 'btn' },
                            { extend: 'print', className: 'btn' }
                        ]
                    },
                    "oLanguage": {
                        "oPaginate": { "sPrevious": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>', "sNext": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>' },
                        "sInfo": "Showing page _PAGE_ of _PAGES_",
                        "sSearch": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>',
                        "sSearchPlaceholder": "Search...",
                       "sLengthMenu": "Results :  _MENU_",
                    },
                    "stripeClasses": [],
                    "lengthMenu": [ 10, 20, 50 ,100],
                    "pageLength": 10
                });
            
                }
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
               if (jqXHR.status === 401) {
                   console.log(jqXHR.responseText);
               } else {
                   console.log(jqXHR.responseText);

               }
            }
       });
   }
   
   function doLogout() { 
       removeJwtToken();
       $userInfo.hide();
       document.location = './login';
   }
   
   $logout.click(doLogout);

    var Selector = {
        mainHeader: '.header.navbar',
        headerhamburger: '.toggle-sidebar',
        fixed: '.fixed-top',
        mainContainer: '.main-container',
        sidebar: '#sidebar',
        sidebarContent: '#sidebar-content',
        sidebarStickyContent: '.sticky-sidebar-content',
        ariaExpandedTrue: '#sidebar [aria-expanded="true"]',
        ariaExpandedFalse: '#sidebar [aria-expanded="false"]',
        contentWrapper: '#content',
        contentWrapperContent: '.container',
        mainContentArea: '.main-content',
        searchFull: '.toggle-search',
        overlay: {
            sidebar: '.overlay',
            cs: '.cs-overlay',
            search: '.search-overlay'
        }
    };

    var categoryScroll = {
        scrollCat: function() {
            var sidebarWrapper = document.querySelectorAll('.sidebar-wrapper [aria-expanded="true"]')[0];
            var sidebarWrapperTop = sidebarWrapper.offsetTop - 20;
            setTimeout(function(){ $('.menu-categories').animate({ scrollTop: sidebarWrapperTop }, 500); }, 500);
        }
    }

    var toggleFunction = {
        sidebar: function($recentSubmenu) {
            $('.sidebarCollapse').on('click', function (sidebar) {
                sidebar.preventDefault();
                getSidebar = $('.sidebar-wrapper');
                    console.log('drill 1')
                if ($recentSubmenu === true) {
                    console.log('drill 2')
                    if ($('.collapse.submenu').hasClass('show')) {
                        console.log('drill 3')
                        $('.submenu.show').addClass('mini-recent-submenu');
                        getSidebar.find('.collapse.submenu').removeClass('show');
                        getSidebar.find('.collapse.submenu').removeClass('show');
                        $('.collapse.submenu').parents('li.menu').find('.dropdown-toggle').attr('aria-expanded', 'false');
                    } else {
                        console.log('drill 4')
                        if ($(Selector.mainContainer).hasClass('sidebar-closed')) {
                            console.log('drill 5')
                            if ($('.collapse.submenu').hasClass('recent-submenu')) {
                                getSidebar.find('.collapse.submenu.recent-submenu').addClass('show');
                                $('.collapse.submenu.recent-submenu').parents('.menu').find('.dropdown-toggle').attr('aria-expanded', 'true');
                                $('.submenu').removeClass('mini-recent-submenu');
                            console.log('drill 6')

                            } else {
                                $('li.active .submenu').addClass('recent-submenu');
                                getSidebar.find('.collapse.submenu.recent-submenu').addClass('show');
                                $('.collapse.submenu.recent-submenu').parents('.menu').find('.dropdown-toggle').attr('aria-expanded', 'true');
                                $('.submenu').removeClass('mini-recent-submenu');
                            console.log('drill 7')
                            }
                        }
                    }
                        console.log('drill 2 end')
                }
                        console.log('end drill')
                $(Selector.mainContainer).toggleClass("sidebar-closed");
                $(Selector.mainHeader).toggleClass('expand-header');
                $(Selector.mainContainer).toggleClass("sbar-open");
                $('.overlay').toggleClass('show');
                $('html,body').toggleClass('sidebar-noneoverflow');
            });
        },
        onToggleSidebarSubmenu: function() {
            $('.sidebar-wrapper').on('mouseenter mouseleave', function(event) {
                event.preventDefault();
                if ($('body').hasClass('alt-menu')) {
                    if ($('.main-container').hasClass('sidebar-closed')) {
                        if (event.type === 'mouseenter') {
                            $('li .submenu').removeClass('show');
                            $('li.active .submenu').addClass('recent-submenu');
                            $('li.active').find('.collapse.submenu.recent-submenu').addClass('show');
                            $('.collapse.submenu.recent-submenu').parents('.menu').find('.dropdown-toggle').attr('aria-expanded', 'true');
                        } else if (event.type === 'mouseleave') {
                            $('li').find('.collapse.submenu').removeClass('show');
                            $('.collapse.submenu.recent-submenu').parents('.menu').find('.dropdown-toggle').attr('aria-expanded', 'false');
                            $('.collapse.submenu').parents('.menu').find('.dropdown-toggle').attr('aria-expanded', 'false');
                        }
                    }
                } else {
                    if ($('.main-container').hasClass('sidebar-closed')) {
                        if (event.type === 'mouseenter') {
                            $(this).find('.submenu.recent-submenu').addClass('show');
                            $('.collapse.submenu.recent-submenu').parents('.menu').find('.dropdown-toggle').attr('aria-expanded', 'true');
                        } else if (event.type === 'mouseleave') {
                            $(this).find('.submenu.recent-submenu').removeClass('show');
                            $('.collapse.submenu.recent-submenu').parents('.menu').find('.dropdown-toggle').attr('aria-expanded', 'false');
                        }
                    }

                }
            })
        },
        offToggleSidebarSubmenu: function () {
            $('.sidebar-wrapper').off('mouseenter mouseleave');
        },
        overlay: function() {
            $('#dismiss, .overlay, cs-overlay').on('click', function () {
                // hide sidebar
                $(Selector.mainContainer).addClass('sidebar-closed');
                $(Selector.mainContainer).removeClass('sbar-open');
                // hide overlay
                $('.overlay').removeClass('show');
                $('html,body').removeClass('sidebar-noneoverflow');
            });
            if(getJwtToken()) {
              $.ajax({
                  url: "/api/me",
                  type: "GET",
                  contentType: "application/json; charset=utf-8",
                  dataType: "json",
                  headers: createAuthorizationTokenHeader(),
                  success: function (data, textStatus, jqXHR) {
                    //console.log(data.firstname+" "+data.lastname);
                    $userName.html(data.firstname+" "+data.lastname);
                    //console.log(data.username);
                    $userPermissions.html(data.username); 
                    if (document.getElementById("zero-config")) { getChannelInformation(); } else if (document.getElementById("taskdetail")) { getTaskInformation();}
                    $userInfo.show();
                  },
                  error: function (jqXHR, textStatus, errorThrown) {
                     if (jqXHR.status === 401) {
                         console.log(jqXHR.responseText);
                     } else {
                         console.log(jqXHR.responseText);

                     }
                  }
               });
             }
             else {
                 document.location = './login';
             }
        },
        search: function() {
            $(Selector.searchFull).click(function(event) {
               $(this).parents('.search-animated').find('.search-full').addClass(ToggleClasses.inputFocused);
               $(this).parents('.search-animated').addClass('show-search');
               $(Selector.overlay.search).addClass('show');
               $(Selector.overlay.search).addClass('show');
            });

            $(Selector.overlay.search).click(function(event) {
               $(this).removeClass('show');
               $(Selector.searchFull).parents('.search-animated').find('.search-full').removeClass(ToggleClasses.inputFocused);
               $(Selector.searchFull).parents('.search-animated').removeClass('show-search');
            });
        }
    }

    var inBuiltfunctionality = {
        mainCatActivateScroll: function() {
            const ps = new PerfectScrollbar('.menu-categories', {
                wheelSpeed:.5,
                swipeEasing:!0,
                minScrollbarLength:40,
                maxScrollbarLength:300
            });
        },
        preventScrollBody: function() {
            $('#sidebar').bind('mousewheel DOMMouseScroll', function(e) {
                var scrollTo = null;

                if (e.type == 'mousewheel') {
                    scrollTo = (e.originalEvent.wheelDelta * -1);
                }
                else if (e.type == 'DOMMouseScroll') {
                    scrollTo = 40 * e.originalEvent.detail;
                }

                if (scrollTo) {
                    e.preventDefault();
                    $(this).scrollTop(scrollTo + $(this).scrollTop());
                }
            });
        },
        languageDropdown: function() {
            var getDropdownElement = document.querySelectorAll('.more-dropdown.language-dropdown .dropdown-item');
            for (var i = 0; i < getDropdownElement.length; i++) {
                getDropdownElement[i].addEventListener('click', function() {
                    document.querySelectorAll('.more-dropdown.language-dropdown .dropdown-toggle > span')[0].innerText = this.getAttribute('data-value');
                    document.querySelectorAll('.more-dropdown .dropdown-toggle > img')[0].setAttribute('src', 'assets/img/' + this.getAttribute('data-img-value') + '.png' );
                })
            }
        },
        appsDropdown: function() {
            var getDropdownElement = document.querySelectorAll('.more-dropdown.apps-dropdown .dropdown-item');
            for (var i = 0; i < getDropdownElement.length; i++) {
                getDropdownElement[i].addEventListener('click', function() {
                    document.querySelectorAll('.more-dropdown.apps-dropdown .dropdown-toggle > span')[0].innerText = this.getAttribute('data-value');
                })
            }
        }
    }

    var _mobileResolution = {
        onRefresh: function() {
            var windowWidth = window.innerWidth;
            if ( windowWidth <= MediaSize.md ) {
                categoryScroll.scrollCat();
                toggleFunction.sidebar();
            }
        },
        
        onResize: function() {
            $(window).on('resize', function(event) {
                event.preventDefault();
                var windowWidth = window.innerWidth;
                if ( windowWidth <= MediaSize.md ) {
                    toggleFunction.offToggleSidebarSubmenu();
                }
            });
        }
        
    }

    var _desktopResolution = {
        onRefresh: function() {
            var windowWidth = window.innerWidth;
            if ( windowWidth > MediaSize.md ) {
                categoryScroll.scrollCat();
                toggleFunction.sidebar(true);
                toggleFunction.onToggleSidebarSubmenu();
            }
            
        },
        
        onResize: function() {
            $(window).on('resize', function(event) {
                event.preventDefault();
                var windowWidth = window.innerWidth;
                if ( windowWidth > MediaSize.md ) {
                    toggleFunction.onToggleSidebarSubmenu();
                }
            });
        }
        
    }

    function sidebarFunctionality() {
        function sidebarCloser() {

            if (window.innerWidth <= 991 ) {


                if (!$('body').hasClass('alt-menu')) {

                    $("#container").addClass("sidebar-closed");
                    $('.overlay').removeClass('show');
                } else {
                    $(".navbar").removeClass("expand-header");
                    $('.overlay').removeClass('show');
                    $('#container').removeClass('sbar-open');
                    $('html, body').removeClass('sidebar-noneoverflow');
                }

            } else if (window.innerWidth > 991 ) {

                if (!$('body').hasClass('alt-menu')) {

                    $("#container").removeClass("sidebar-closed");
                    $(".navbar").removeClass("expand-header");
                    $('.overlay').removeClass('show');
                    $('#container').removeClass('sbar-open');
                    $('html, body').removeClass('sidebar-noneoverflow');
                } else {
                    $('html, body').addClass('sidebar-noneoverflow');
                    $("#container").addClass("sidebar-closed");
                    $(".navbar").addClass("expand-header");
                    $('.overlay').addClass('show');
                    $('#container').addClass('sbar-open');
                    $('.sidebar-wrapper [aria-expanded="true"]').parents('li.menu').find('.collapse').removeClass('show');
                }
            }

        }

        function sidebarMobCheck() {
            if (window.innerWidth <= 991 ) {

                if ( $('.main-container').hasClass('sbar-open') ) {
                    return;
                } else {
                    sidebarCloser()
                }
            } else if (window.innerWidth > 991 ) {
                sidebarCloser();
            }
        }

        sidebarCloser();

        $(window).resize(function(event) {
            sidebarMobCheck();
        });

    }

    return {
        init: function() {
            toggleFunction.overlay();
            toggleFunction.search();
            /*
                Desktop Resoltion fn
            */
            _desktopResolution.onRefresh();
            _desktopResolution.onResize();

            /*
                Mobile Resoltion fn
            */
            _mobileResolution.onRefresh();            
            _mobileResolution.onResize();

            sidebarFunctionality();

            /*
                In Built Functionality fn
            */
            inBuiltfunctionality.mainCatActivateScroll();
            inBuiltfunctionality.preventScrollBody();
            inBuiltfunctionality.languageDropdown();
            inBuiltfunctionality.appsDropdown();
        }
    }

}();
