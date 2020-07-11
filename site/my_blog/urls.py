"""my_blog URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from article import views


urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^archives/$', views.archives, name='archives'),
    url(r'^$', views.home, name='home'),

    url(r'^get_all_category/$', views.get_all_category, name='get_all_category'),
    url(r'^(?P<id>\d+)/$', views.detail, name='detail'),
    url(r'^show_one_category/(?P<category>.*)/$', views.show_one_category, name='show_one_category'),
    url(r'^search/$', views.search, name='search'),

    url(r'^add_likes/$', views.add_likes, name='add_likes'),
    url(r'^hot_articles/$', views.hot_articles, name='hot_articles'),
    url(r'^related_articles/$', views.related_articles, name='related_articles'),
    url(r'^get_prev_next_articles/$', views.get_prev_next_articles,
        name='get_prev_next_articles'),
    url(r'^add_reads/$', views.add_reads, name='add_reads'),
    url(r'^search/$', views.search, name='search'),
    url(r'^go_album/$', views.go_album, name='go_album'),
    url(r'^go_email/$', views.go_email, name='go_email'),
    url(r'^send_email/$', views.send_email, name='send_email'),
]
