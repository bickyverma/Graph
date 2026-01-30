import sys
input = sys.stdin.readline

N, S, L = map(int, input().split())
M, K, P = map(int, input().split())

sp = S // P
R = N * sp * K
oxc_per_plane = M // P

# Persistent OXC topology
oxc = [[-1] * R for _ in range(M)]

def port_id(group, spine, link):
    return group * sp * K + (spine % sp) * K + link

for _ in range(5):
    Q = int(input())
    flows = []
    for _ in range(Q):
        gA, lA, gB, lB = map(int, input().split())
        flows.append((gA, lA, gB, lB))

    leaf_cnt = dict()
    routes = []

    for gA, lA, gB, lB in flows:
        cA = leaf_cnt.get((gA, lA), 0)
        cB = leaf_cnt.get((gB, lB), 0)
        leaf_cnt[(gA, lA)] = cA + 1
        leaf_cnt[(gB, lB)] = cB + 1

        spineA = (lA + cA) % S
        spineB = (lB + cB) % S
        link = 0

        plane = spineA // sp
        oxc_id = plane * oxc_per_plane + (cA % oxc_per_plane)

        pA = port_id(gA, spineA, link)
        pB = port_id(gB, spineB, link)

        # Only add connection if missing
        if oxc[oxc_id][pA] == -1 and oxc[oxc_id][pB] == -1:
            oxc[oxc_id][pA] = pB
            oxc[oxc_id][pB] = pA

        routes.append((spineA, link, oxc_id, spineB, link))

    # Output OXC topology
    for i in range(M):
        print(" ".join(map(str, oxc[i])))

    # Output routes
    for r in routes:
        print(*r)
