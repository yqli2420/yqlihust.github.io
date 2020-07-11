# -*- coding: utf-8 -*-
from django.shortcuts import render
from django.http import HttpResponse
from article.models import Article
from django.http import Http404
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger
from django.template import RequestContext
from django.shortcuts import render_to_response
from django.template.context_processors import csrf
from django.core.mail import send_mail
import json
from django.core import serializers
import markdown

# Create your views here.
def home(request):
    posts = Article.objects.all()  #获取全部的Article对象
    paginator = Paginator(posts, 5) #每页显示两个
    page = request.GET.get('page')
    try:
        post_list = paginator.page(page)
    except PageNotAnInteger:
        post_list = paginator.page(1)
    except EmptyPage:
        post_list = paginator.page(paginator.num_pages)

    return render(request, 'home.html', {'post_list': post_list})


def get_all_category(request):
    category_list = {"C++", "语言", "Java"}
    return HttpResponse(category_list)


def detail(request, id):
    try:
        post = Article.objects.get(id=str(id))
    except Article.DoesNotExist:
        raise Http404
    return render(request, 'post.html', {'post': post})


def show_one_category(request, category):
    print 'show_one_category'
    posts = Article.objects.filter(category=str(category))
    paginator = Paginator(posts, 2)  # 每页显示两个
    page = request.GET.get('page')
    try:
        post_list = paginator.page(page)
    except PageNotAnInteger:
        post_list = paginator.page(1)
    except EmptyPage:
        post_list = paginator.paginator(paginator.num_pages)
    return render(request, 'one-category.html', {'post_list': post_list, 'category': str(category)})


def archives(request):
    try:
        post_list = Article.objects.all()
    except Article.DoesNotExist:
        raise Http404
    return render(request, 'archives.html', {'post_list': post_list, 'error': False})

def hot_articles(request):
    try:
        posts=(Article.objects.values('title','id').order_by("-reads")[:6])
    except Article.DoesNotExist:
        raise Http404
    return HttpResponse(json.dumps(list(posts)),content_type="application/json")


def related_articles(request):
    try:
        category=str(request.POST["category"])
        related_blogs=(Article.objects.filter(category=category).values('title','id','category').order_by("-reads")[:6])
    except Article.DoesNotExist:
        raise Http404
    return HttpResponse(json.dumps(list(related_blogs)),content_type="application/json")

def get_prev_next_articles(request):
    try:
        blog_id = request.POST["blog_id"]
        prev={}
        next={}
        prev_blog=Article.objects.filter(id__lt=int(blog_id)).values('title','id').order_by("-id")[0:1];
        next_blog=Article.objects.filter(id__gt=int(blog_id)).values('title','id').order_by("id")[0:1];
        if len(prev_blog):
            prev=prev_blog[0]
        if len(next_blog):
            next=next_blog[0]
        posts=[prev,next]
    except Article.DoesNotExist:
        raise Http404
    return HttpResponse(json.dumps(list(posts)),content_type="application/json")

def add_likes(request):
    try:
        blog_id = request.POST["blog_id"]
        blog = Article.objects.get(id=int(blog_id))
        likes = blog.likes + 1
        blog.likes = likes
        blog.save()
    except Article.DoesNotExist:
        raise Http404
    r = HttpResponse(str(likes))
    return r

def add_reads(request):
    try:
        blog_id = request.POST["blog_id"]
        blog = Article.objects.get(id=int(blog_id))
        reads = blog.reads + 1
        blog.reads = reads
        blog.save()
    except Article.DoesNotExist:
        raise Http404
    r = HttpResponse(str(reads))
    return r


def search(request):
    key = request.GET.get('key')
    posts = Article.objects.filter(title__contains=str(key))
    paginator = Paginator(posts, 2)  # 每页显示两个
    page = request.GET.get('page')
    try:
        post_list = paginator.page(page)
    except PageNotAnInteger:
        post_list = paginator.page(1)
    except EmptyPage:
        post_list = paginator.page(paginator.num_pages)
    return render(request, 'search-result.html', {'post_list': post_list, 'key': str(key)})


def go_album(request):

    return render(request, 'album.html')

def go_email(request):

    return render(request,'email.html')

def send_email(request):
    c={}
    c.update(csrf(request))

    to_who=request.POST.get('to')
    from_who=request.POST.get('from')
    subject=request.POST.get('subject')
    content=request.POST.get('content')

    send_mail(subject, content,from_who,[to_who],fail_silently=False)
    return render_to_response('email.html',c)

