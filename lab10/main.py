def getInput(line):
    line = line.split("|")

    A = []
    alphabet = line[0].split('{')[1].split('}')[0].split(',')
    for a in alphabet:
        if a[0] == ' ':
            A.append(a[1])
        else:
            A.append(a[0])
    print("Aplhabet: ", A)

    I = line[1].split('{')[1].split('}')[0].split('(')[1:]
    independence_rel = []
    I_print = [(a[0], a[3]) for a in I]
    independence_rel = [a[0] + a[3] for a in I]
    print("Independence Relations: ", I_print)

    word = line[2].split(' ')[3]
    print("Word: ", word)

    return A, independence_rel, word

def getDependenceRelation(A, I):
    D = [[0 for j in range(len(A))] for i in range(len(A))]
    for i in I:
        D[A.index(i[0])][A.index(i[1])] = -1
    dependence_rel = []
    for i in range(len(A)):
        for j in range(len(A)):
            if D[i][j] == 0:
                dependence_rel.append((A[i], A[j]))
    print("Dependence Relations: ", dependence_rel)
    return dependence_rel


def getTrace(word, I):
    trace = []
    trace.append(word)
    added = True
    while added:
        added = False
        for t in trace:
            added = False
            for i in range(len(t)-1):
                pair = t[i] + t[i+1]
                if pair in I:
                    new_t = t[:i] + t[i+1] + t[i] + t[i+2:]
                    if new_t not in trace:
                        added = True
                        trace.append(new_t)
    trace = list(set(trace))
    print(f"Trace of word {word}: {trace}")
    return trace


def dependenceGraph(word, I):
    edges = []
    edges_to_remove = []
    graph = "digraph{\n"
    for i in range(len(word)-1, -1, -1):
        for j in range(i+1, len(word)):
            pair = word[i] + word[j]
            edge = (str(i+1), str(j+1))
            if pair not in I:
                edges.append(edge)

    for e in edges:
        e1 = int(e[0])
        e2 = int(e[1])

        for i in range(e1 + 1, e2):
            pair1 = (e[0], str(i))
            pair2 = (str(i), e[1])
            if pair1 in edges and pair2 in edges:
                edges_to_remove.append(e)
    edges = [x for x in edges if x not in edges_to_remove]

    for e in edges:
        graph += "\n" + e[0] + " -> " + e[1]
    for i in range(len(word)):
        graph += "\n" + str(i+1) + "[label=" + word[i] + "]\n"
    graph += "\n"
    graph += "}"

    print("Graph: ", graph)

    return edges

def fnfGraph(edges, word):
    edges_fnf = edges.copy()
    active_vertices = []
    for i in range(len(word)):
        active_vertices.append(i+1)

    result = []
    idx = 0

    while active_vertices:
        result.append([])
        no_incoming_edges = []
        for a in active_vertices:
            flag = False
            for e in edges_fnf:
                if e[1] == str(a):
                    flag = True
                    break
            if not flag:
                no_incoming_edges.append(a)
        for i in no_incoming_edges:
            result[idx].append(word[i-1])
            active_vertices.remove(i)
            tmp_edges = []
            for e in edges_fnf:
                if e[0] != str(i):
                    tmp_edges.append(e)
            edges_fnf = tmp_edges

        idx += 1

    fnf_graph = ""
    for res in result:
        fnf_graph += "["
        for i in res:
            fnf_graph += i
        fnf_graph+="]"
    print("FNF from graph: ", fnf_graph)

def fnf(w, I, D):
    W = [True for i in range(len(w))]

    stacks = []

    for i in range(len(w)):
        stack = ''
        if W[i]:
            W[i] = False
            stack += w[i]

            to_remove = []

            for j in range(i, len(w)):
                if W[j] and w[i]+w[j] in I:
                    helper = False
                    for k in range(i, j):
                        if W[k] and (w[j], w[k]) in D:
                            helper = True
                            break
                    if not helper:
                        stack += w[j]
                        to_remove.append(j)
            for r in to_remove:
                W[r] = False
            stacks.append(stack)

    fnf_print = 'FNF([w]) = '
    for b in stacks:
        fnf_print += '(' + b + ')'

    print(fnf_print)

def main(input):
    alphabet, independence_rel, word = getInput(input)
    dependence_rel = getDependenceRelation(alphabet, independence_rel)
    trace = getTrace(word, independence_rel)
    fnf(word,independence_rel, dependence_rel)
    edges = dependenceGraph(word, independence_rel)
    fnfGraph(edges, word)

# input = "A = {a, b, c, d} | I = {(a, d), (d, a), (b, c), (c, b)} | w = baadcb"
input = "A = {a, b, c, d, e, f} | I = {(a, d), (d, a), (b, e), (e, b), (c, d), (d, c), (c, f), (f, c)} | w = acdcfbbe"
main(input)
