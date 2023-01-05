
import numpy as np
import pandas as pd
import random
import math
import matplotlib.pyplot as plt
from numpy.random import randint

data = pd.read_csv('TSPcities1000.txt',sep='\s+',header=None)
data = pd.DataFrame(data)


x = data[1]
y = data[2]
plt.plot(x, y,'r.')
plt.show()


def createRandomRoute():
    tour = [[i] for i in range(1000)]
    random.shuffle(tour)
    return tour

# plot the tour - Adjust range 0..len, if you want to plot only a part of the tour.
def plotCityRoute(route):
    for i in range(0, len(route)):
        plt.plot(x[i:i + 2], y[i:i + 2], 'ro-')
    plt.show()

# Alternativ kode:
#  for i in range(0, len(route)-1):
#     plt.plot([x[route[i]],x[route[i+1]]], [y[route[i]],y[route[i+1]]], 'ro-')



def fill_with_parent1_genes(child, parent, genes_n):
    start_at = randint(0, len(parent) - genes_n - 1)
    finish_at = start_at + genes_n
    for i in range(start_at, finish_at):
        child[i] = parent[i]


def fill_with_parent2_genes(child, parent):
    j = 0
    for i in range(0, len(parent)):
        if child[i] == None:
            while parent[j] in child:
                j += 1
            child[i] = parent[j]
            j += 1

def findBestRoute(routes, scores):
    size = len(scores)
    # create routes
    route1 = random.randint(0, size - 1)
    route2 = random.randint(0, size - 1)
    # find fitness scores
    route1fitness = scores[route1]
    route2fitness = scores[route2]
    # return lowest score
    if route1fitness <= route2fitness:
        return routes[route1]
    else:
        return routes[route2]



def mutate(route_to_mut, k_mut_prob):

    if random.random() < k_mut_prob:

            # two random indices:
        mut_pos1 = random.randint(0,len(route_to_mut)-1)
        mut_pos2 = random.randint(0,len(route_to_mut)-1)

            # if they're the same, skip to the chase
        if mut_pos1 == mut_pos2:
            return route_to_mut

            # Otherwise swap them:
        city1 = route_to_mut[mut_pos1]
        city2 = route_to_mut[mut_pos2]

        route_to_mut[mut_pos2] = city1
        route_to_mut[mut_pos1] = city2


def createRoutes(amount_of_routes, routes):
    for i in range(amount_of_routes):
        routes[i] = createRandomRoute()


# calculate distance between cities
def distancebetweenCities(city1x, city1y, city2x, city2y):
    xDistance = abs(city1x-city2x)
    yDistance = abs(city1y-city2y)
    distance = math.sqrt((xDistance * xDistance) + (yDistance * yDistance))
    return distance

# calculates the fitness by adding all distances between cities together
def distanceFitness(tour):
    finaldistance = 0
    for i in range(len(tour) - 1):
        city1 = tour[i][0]
        city2 = tour[i+1][0]
        dist = distancebetweenCities(x[city1], y[city1], x[city2], y[city2])
        finaldistance += dist
    return finaldistance

# finds score of all routes
def calculateScore(routes):
    score = np.empty(len(routes))
    for i in range(len(routes)):
        score[i] = distanceFitness(routes[i])
    return score


# starting values
best_score_progress = []  # Tracks progress
generations = 50
generationlength = 40 # has to be even
mutationrate = 0.75
# generate routes
usedroutes = [None] * generationlength
createRoutes(generationlength, usedroutes)
scores = calculateScore(usedroutes)
# find the lowest score
best_score = np.min(scores) # divide by 1000 if using TSPcities1000, 100 if using TSPcities100, etc...
best_score_progress.append(best_score)

for generation in range(generations):
    currentgeneration = []

    for i in range(int(generationlength / 2)):
        # parents selected
        parent1 = findBestRoute(usedroutes, scores)
        parent2 = findBestRoute(usedroutes, scores)
        # fills children with parent genes
        child1 = [None] * len(parent1)
        fill_with_parent1_genes(child1, parent1, 300)
        fill_with_parent2_genes(child1, parent2)
        child2 = [None] * len(parent1)
        fill_with_parent1_genes(child2, parent2, 150)
        fill_with_parent2_genes(child2, parent1)
        # mutates children
        mutate(child1, mutationrate)
        mutate(child2, mutationrate)
        # saves children as the next generation
        currentgeneration.append(child1)
        currentgeneration.append(child2)

    # replaces routes with the new generation routes
    usedroutes = currentgeneration.copy()
    # finds lowest score of new generation
    scores = calculateScore(usedroutes)
    best_score = np.min(scores) # divide by 1000 to show the best fitness
    best_score_progress.append(best_score)

plt.plot(best_score_progress)
plt.xlabel('Generation')
plt.ylabel('Best Fitness - route length - in Generation')
plt.show()
