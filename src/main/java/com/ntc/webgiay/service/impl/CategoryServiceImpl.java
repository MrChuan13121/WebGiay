package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.entity.Category;
import com.ntc.webgiay.repository.BrandRepository;
import com.ntc.webgiay.repository.CategoryRepository;
import com.ntc.webgiay.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Category> getListCategoryById(int id){

        List<Category> categoryList = new List<Category>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Category> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Category category) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Category> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Category> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Category get(int index) {
                return null;
            }

            @Override
            public Category set(int index, Category element) {
                return null;
            }

            @Override
            public void add(int index, Category element) {

            }

            @Override
            public Category remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Category> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Category> listIterator(int index) {
                return null;
            }

            @Override
            public List<Category> subList(int fromIndex, int toIndex) {
                return null;
            }
        };


        return categoryList;
    }
}
