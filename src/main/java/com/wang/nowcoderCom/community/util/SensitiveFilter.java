package com.wang.nowcoderCom.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    //替换符
    private static final String REPLACEMENT = "***";

    //根节点
    private TrieNode root = new TrieNode();

    //初始化前缀树
    @PostConstruct
    public void init() {
        try (
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyWord;
            while ((keyWord = br.readLine()) != null) {
                this.addKeyWord(keyWord);
            }

        } catch (IOException e) {
            logger.error("加载敏感词文件失败：" + e.getMessage());

        }

    }

    // 将一个敏感词添加到前缀和中
    public void addKeyWord(String keyWord) {
        TrieNode tempNode = root;
        for (int i = 0; i < keyWord.length(); i++) {
            char c = keyWord.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);
            if (subNode == null) {
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }
            tempNode = subNode;
            if (i == keyWord.length() - 1)
                tempNode.setKeyWordEnd(true);
        }
    }

    /**
     * 过滤敏感词
     *
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text) {
        if (StringUtils.isBlank(text)) return null;

        //定义三个指针 分别指向前缀树root,text的待过滤部分开头和结尾
        TrieNode tempNode = root;
        int begin = 0, position = 0;
        //结果
        StringBuilder sb = new StringBuilder();

        while (position < text.length()) {
            char c = text.charAt(position);
            //跳过符号
            if (isSymbol(c)) {
                // 若指针1处于根节点，将此符号计入结果，让指针2向下走一步
                if (tempNode == root) {
                    sb.append(c);
                    begin++;
                }
                //无论符号在开头或中间，指针3都向下走一步
                position++;
                continue;
            }

            //检查下级节点
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                //以begin为开头的字符串不是敏感词
                sb.append(text.charAt(begin));
                //进入下一个位置
                position = ++begin;
                //重新指向根节点
                tempNode = root;
            } else if (tempNode.isKeyWordEnd()) {
                //发现敏感词，替换
                sb.append(REPLACEMENT);
                begin = ++position;
                //重新指向根节点
                tempNode = root;
            } else {
                // 检查下一个字符
                position++;
            }
        }

        //将最后一批字符计入结果
        sb.append(text.substring(begin));

        return sb.toString();
    }


    //判断是否为符号
    private boolean isSymbol(Character c) {
        // 0x2E80 - 0x9FFF 为东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    //前缀树
    private class TrieNode {
        // 关键词结束标识
        private boolean isKeyWordEnd = false;

        // 子节点(key-下级节点字符， value-下级节点)
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeyWordEnd() {
            return isKeyWordEnd;
        }

        public void setKeyWordEnd(boolean keyWordEnd) {
            isKeyWordEnd = keyWordEnd;
        }

        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }
    }

}
