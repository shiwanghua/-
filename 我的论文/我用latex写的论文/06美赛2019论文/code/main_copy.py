# -*- coding: utf-8 -*-
from typing import List, Union
import pandas as pd
import math
'''class item_:

    def __init__(self, item_d: int=None, item_w: int=None, item_h: int=None):

        self.d = item_d
        self.w = item_w
        self.h = item_h

#class box_:

    def __init__(self, box_d: int=None, box_w: int=None, box_h: int=None):

        self.D = box_d
        self.W = box_w
        self.H = box_h'''

'''def split_space(x,y,z):
    df = pd.DataFrame(columns=('d', 'w', 'h', 'judge'))
    df1 = pd.DataFrame(columns=('d', 'w', 'h', 'judge'))
    break_flag = False
    for i in range(x):
        print(i)
        for j in range(y):
            for t in range(z):
                if df[(df['d'] ==i)&(df['w'] ==j)&(df['h'] ==t)]['judge'] != 1:
                    df1 = df1.append(
                        pd.DataFrame({'d': [i], 'w': [j], 'h': [t], 'judge': 1}),
                        ignore_index=True)
                else:
                    break_flag = True
                    break
            if break_flag == True:
                break
        if break_flag == True:
            break
    if break_flag == True:
        return False
    else:
        df = pd.merge'''
'''class judge():

    def __init__(self,select_point,select_item):
        self.D = box_d
        self.W = box_w
        self.H = box_h
        self.select_point = select_point
        self.select_item = select_item


    def judge_box(self):
        if self.select_point[0] + self.select_item[0] < new_box or self.select_point[1] + self.select_item[1] \
                < restrictions_w or self.select_point[2] + self.select_item[2] < restrictions_h
            return False'''


def pack_products_into_restrictions(items: List[list],
                                    box: tuple):
    def judge_box(select_point, select_item):
        if select_point[0] + select_item[0] <= restrictions_d and \
                select_point[1] + select_item[1] <= restrictions_w and \
                select_point[2] + select_item[2] <= restrictions_h:
            return True
        else:
            return False

    def split_space(x, y, z):

        df = pd.DataFrame(columns=('d', 'w', 'h', 'judge'))
        for i in range(x + 1):
            print('rate of progress :', i / x)
            for j in range(y + 1):
                for t in range(z + 1):
                    df = df.append(
                        pd.DataFrame({'d': [i], 'w': [j], 'h': [t], 'judge': 0}),
                        ignore_index=True)
        return df

    def judge_items_limit(space, select_point, select_item):
        x, y, z = select_point[0], select_point[1], select_point[2]
        d, w, h = select_item[0], select_item[1], select_item[2]

        if (space[(space['d'] == x) & (space['w'] == y) & (space['h'] == z)]['judge'] == 1).all() | \
                (space[(space['d'] == x + d) & (space['w'] == y) & (space['h'] == z)]['judge'] == 1).all() | \
                (space[(space['d'] == x) & (space['w'] == y + w) & (space['h'] == z)]['judge'] == 1).all() | \
                (space[(space['d'] == x) & (space['w'] == y) & (space['h'] == z + h)]['judge'] == 1).all() | \
                (space[(space['d'] == x + d) & (space['w'] == y + w) & (space['h'] == z)]['judge'] == 1).all() | \
                (space[(space['d'] == x + d) & (space['w'] == y) & (space['h'] == z + h)]['judge'] == 1).all() | \
                (space[(space['d'] == x) & (space['w'] == y + w) & (space['h'] == z + h)]['judge'] == 1).all() | \
                (space[(space['d'] == x + d) & (space['w'] == y + w) & (space['h'] == z + h)]['judge'] == 1).all():
            return False
        else:
            return True

    def put_item(space, select_point, select_item):
        x, y, z = select_point[0], select_point[1], select_point[2]
        d, w, h = select_item[0], select_item[1], select_item[2]
        for i in range(x, x + d):
            for j in range(y, y + w):
                for t in range(z, z + h):
                    index_item = space[(space['d'] == i) & (space['w'] == j) & (space['h'] == t)].index
                    space.loc[index_item, 'judge'] = 1
        return space

    def judge_x_line_limit(select_point, select_item, x_line):
        if select_point[0] + select_item[0] <= x_line:
            return True
        else:
            return False

    def judge_z_line_limit(select_point, select_item, z_line):
        if select_point[2] + select_item[2] <= z_line:
            return True
        else:
            return False

#############################################################################
    restrictions_d, restrictions_w, restrictions_h = box
    item_space = split_space(restrictions_d, restrictions_w, restrictions_h)
    lx, lz = 0, 0
    put_set = [[0, 0, 0]]
    select_item = []

    for i in range(len(items)):
        put_set.sort(key=lambda x: (x[1], x[0], x[2]))
        item = items[i]
        item_d, item_w, item_h = item
        if item_d > restrictions_d or item_w > restrictions_w or item_h > restrictions_h:
            continue
        flag = 'F'
        repeat = 're1'
        while repeat == 're1' or repeat == 're2':
            if repeat == 're2' or lx == restrictions_h:
                repeat = 're3'
            for index, point in enumerate(put_set):
                if judge_box(point, item) and \
                        judge_x_line_limit(point, item, lx) and \
                        judge_z_line_limit(point, item, lz) and \
                        judge_items_limit(item_space, point, item):
                    item_space = put_item(item_space, point, item)
                    flag = 'T'
                    repeat = 're3'
                    break
            if flag == 'F':
                if lx == 0 or lx == restrictions_d:
                    if judge_box((0, 0, lz), item) and \
                            judge_items_limit(item_space, (0, 0, lz), item):
                        item_space = put_item(item_space, (0, 0, lz), item)
                        point[0], point[1], point[2] = 0, 0, lz
                        index = put_set.index([point[0], point[1], point[2]])
                        flag = 'T'
                        repeat = 're3'
                        lx, lz = item_d, lz + item_h
                    '''else:
                        if lz < restrictions_d and repeat == 're2':
                            lx, lz, i = restrictions_d, restrictions_h, i - 1
                            repeat = 're3'''
                else:
                    for index, point in enumerate(put_set):
                        if (point[0] == lx and point[1] == 0) and \
                                judge_z_line_limit(point, item, lz) and \
                                judge_box(point, item) and \
                                judge_items_limit(item_space, point, item):
                            item_space = put_item(item_space, point, item)
                            flag = 'T'
                            repeat = 're3'
                            lx = lx + item_d
                            break
                    if flag == 'F' and repeat == 're1':
                        lx = restrictions_h
                        repeat = 're2'
            if flag == 'T':
                select_item.append([i, item])
                put_set.pop(index)
                put_set.append([point[0] + item_d, point[1], point[2]])
                put_set.append([point[0], point[1] + item_w, point[2]])
                put_set.append([point[0], point[1], point[2] + item_h])

    space_ratio = item_space['judge'].value_counts(sort=True)[1] / (restrictions_d * restrictions_w * restrictions_h)
    print('space_ratio:', space_ratio)
    print('itemcurrent:', select_item)
    return space_ratio, select_item


pass


