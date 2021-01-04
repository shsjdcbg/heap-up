package pers.dyx.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 树操作
 *
 * @author dyx
 * @date 2018/3/27
 * @since 1.0
 */
public class TreeNode {

    private Integer id;
    private Integer pid;
    private String name;
    private List<TreeNode> children;

    TreeNode(Integer id, Integer pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public static void main(String[] args) {
        List<TreeNode> list = new ArrayList<>();
        list.add(new TreeNode(1, 0, "1"));
        list.add(new TreeNode(2, 0, "2"));
        list.add(new TreeNode(3, 2, "3"));
        list.add(new TreeNode(4, 3, "4"));
        list.add(new TreeNode(5, 4, "5"));
        list.add(new TreeNode(6, 5, "6"));

        List<TreeNode> treeList = new ArrayList<TreeNode>();
        List<TreeNode> treeList1 = new ArrayList<TreeNode>();
        List<TreeNode> treeList2 = new ArrayList<TreeNode>();
        List<TreeNode> treeList3 = new ArrayList<TreeNode>();
        //方法一、
        treeList = listGetStree(list);
        treeList1 = listToTree(list);
        treeList2 = toTree(list);

        System.out.println(JSON.toJSONString(treeList));
        System.out.println(JSON.toJSONString(treeList1));
        System.out.println(JSON.toJSONString(treeList2));
    }

    private static List<TreeNode> listGetStree(List<TreeNode> list) {
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        for (TreeNode tree : list) {
            //找到根
            if (tree.getPid() == 0) {
                treeList.add(tree);
            }
            //找到子
            for (TreeNode treeNode : list) {
                if (Objects.equals(treeNode.getPid(), tree.getId())) {
                    if (tree.getChildren() == null) {
                        tree.setChildren(new ArrayList<TreeNode>());
                    }
                    tree.getChildren().add(treeNode);
                }
            }
        }
        return treeList;
    }

    /**
     * 方法二、
     *
     * @param list
     * @return
     */
    public static List<TreeNode> listToTree(List<TreeNode> list) {
        //用递归找子。
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        for (TreeNode tree : list) {
            if (tree.getPid() == 0) {
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }

    private static TreeNode findChildren(TreeNode tree, List<TreeNode> list) {
        for (TreeNode node : list) {
            if (Objects.equals(node.getPid(), tree.getId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<TreeNode>());
                }
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }

    /**
     * 方法三
     *
     * @param list
     * @return
     */
    private static List<TreeNode> toTree(List<TreeNode> list) {
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        for (TreeNode tree : list) {
            if (tree.getPid() == 0) {
                treeList.add(tree);
            }
        }
        for (TreeNode tree : list) {
            toTreeChildren(treeList, tree);
        }
        return treeList;
    }

    private static void toTreeChildren(List<TreeNode> treeList, TreeNode tree) {
        for (TreeNode node : treeList) {
            if (Objects.equals(tree.getPid(), node.getId())) {
                if (node.getChildren() == null) {
                    node.setChildren(new ArrayList<TreeNode>());
                }
                node.getChildren().add(tree);
            }
            if (node.getChildren() != null) {
                toTreeChildren(node.getChildren(), tree);
            }
        }
    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}

